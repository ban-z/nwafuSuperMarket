package com.banzh.nwafusupermarket.activity.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.activity.HomeActivity;
import com.banzh.nwafusupermarket.data.LoginResult;
import com.banzh.nwafusupermarket.data.RegisterResult;
import com.banzh.nwafusupermarket.databinding.ActivityLoginCustomBinding;
import com.banzh.nwafusupermarket.network.HttpConstants;
import com.banzh.nwafusupermarket.network.myHttp.myHttpClient.CommonHttpClient;
import com.banzh.nwafusupermarket.network.myHttp.myRequest.CommonRequest;
import com.banzh.nwafusupermarket.network.myHttp.myRequest.RequestParams;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginCustomBinding loginBinding;
    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnDirectLogin;
    private ProgressBar loadingProgressBar;

    private static int CODE_FOR_PERMISSIONS = 123;
    private static String CODE_FOR_LOGIN_SUCCESS = "登陆成功";
    private static String CODE_FOR_REGISTER_SUCCESS = "注册成功";
    private static String CODE_FOR_REPEAT_REGISTER = "用户名已存在";
    private static String TAG = "LoginActivity";
    private Thread threadLogin;
    private Thread threadRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_custom);
        applyForPermissions();
        initView();
    }

    private void initView() {
        etUserName = loginBinding.etUserName;
        etPassword = loginBinding.etPassword;
        btnLogin = loginBinding.btnLogin;
        btnRegister = loginBinding.btnRegister;
        btnDirectLogin = loginBinding.btnDirectLogin;
        loadingProgressBar = loginBinding.loading;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                loadingProgressBar.setVisibility(View.VISIBLE);
                final String userName = etUserName.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                final RequestParams params = new RequestParams();
                params.put("username", userName);
                params.put("password", password);
                /*Log.d(TAG, "onClick: user login information: userName = " + userName + " && password = " + password);*/
                threadLogin = new Thread(new Runnable() {
                    @Override
                    public void run() {
                            CommonHttpClient.okHttpClient.newCall(CommonRequest.buildPostRequest(HttpConstants.LOGIN, params)).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast toast = Toast.makeText(LoginActivity.this, "登录失败，请检查登录信息", Toast.LENGTH_SHORT);
                                            toast.setGravity(Gravity.CENTER, 0 ,0);
                                            toast.show();
                                            etUserName.setText("");
                                            etPassword.setText("");
                                            loadingProgressBar.setVisibility(View.GONE);
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                loadingProgressBar.setVisibility(View.GONE);
                                                String resultJson = response.body().string();
                                                //Log.d(TAG, "run: reslut json:" + resultJson);
                                                LoginResult result = JSON.parseObject(resultJson, LoginResult.class);
                                                //Log.d(TAG, "run: result: result.mes = " + result.getData().getMes() + " && token = " + result.getData().getToken());
                                                if (result.getData().getMes().equals(CODE_FOR_LOGIN_SUCCESS)){
                                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                    intent.putExtra("username", userName);
                                                    intent.putExtra("password", password);
                                                    intent.putExtra("Token", result.getData().getToken().trim());
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                });
                threadLogin.start();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                loadingProgressBar.setVisibility(View.VISIBLE);
                final String userName = etUserName.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                final RequestParams params = new RequestParams();
                params.put("username", userName);
                params.put("password", password);
                threadRegister = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CommonHttpClient.okHttpClient.newCall(CommonRequest.buildPostRequest(HttpConstants.REGISTER, params)).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(LoginActivity.this, "注册失败，不要问我为什么，我也不知道", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0 ,0);
                                        toast.show();
                                        etUserName.setText("");
                                        etPassword.setText("");
                                        loadingProgressBar.setVisibility(View.GONE);
                                    }
                                });
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                try {
                                    String resultJson = response.body().string();
                                    RegisterResult result = JSON.parseObject(resultJson, RegisterResult.class);
                                    if (result.getMsg().equals(CODE_FOR_REGISTER_SUCCESS)){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                loadingProgressBar.setVisibility(View.GONE);
                                                Toast toast = Toast.makeText(LoginActivity.this, "恭喜你，注册成功，请点击登录", Toast.LENGTH_SHORT);
                                                toast.setGravity(Gravity.CENTER, 0 ,0);
                                                toast.show();
                                            }
                                        });
                                    }else if (result.getMsg().equals(CODE_FOR_REPEAT_REGISTER)){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                loadingProgressBar.setVisibility(View.GONE);
                                                Toast toast = Toast.makeText(LoginActivity.this, "用户名已存在，请重新注册", Toast.LENGTH_SHORT);
                                                toast.setGravity(Gravity.CENTER, 0 ,0);
                                                toast.show();
                                            }
                                        });
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                threadRegister.start();
            }
        });

        btnDirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = "测试";
                final String password = "123456";
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("username", userName);
                intent.putExtra("password", password);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void applyForPermissions() {
        int hasInternetPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.INTERNET);
        if (hasInternetPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                    Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_FOR_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_FOR_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(LoginActivity.this, "你可以正常使用此应用", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "需要获取权限才能使用此应用", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
