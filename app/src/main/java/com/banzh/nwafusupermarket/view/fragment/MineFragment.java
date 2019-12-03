package com.banzh.nwafusupermarket.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.activity.HomeActivity;
import com.banzh.nwafusupermarket.databinding.FragmentMineBinding;
import com.banzh.nwafusupermarket.viewmodel.UserViewModel;
import com.bumptech.glide.Glide;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends Fragment {

    FragmentMineBinding mineBinding;

    private static int PERMISSON_WRITE_EXTERNAL_STORAGE = 2882;
    private static int PICK_IAMGE_CODE = 2883;

    private UserViewModel userViewModel;
    private ImageView imgHeadPortrait;
    private TextView tvUserName;
    private TextView tvUserSign;
    private EditText etInputUserSign;

    private String TAG = "MineFragment";

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mineBinding = FragmentMineBinding.inflate(inflater);
        //在其父活动中已经声明过
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        /*Log.d(TAG, "onCreateView: display:\n\t\t\t\t\t" + userViewModel.getUser().getValue().getUserName() + "\n\t\t\t\t\t" +
                userViewModel.getUser().getValue().getUserSignal());*/
        mineBinding.setUserViewModel(userViewModel);
        return mineBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        imgHeadPortrait = mineBinding.imgMine;
        tvUserName = mineBinding.tvMineName;
        tvUserSign = mineBinding.tvMineSign;

        imgHeadPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "长按可以更换头像哦！", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0 , 0);
                toast.show();
            }
        });

        imgHeadPortrait.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    /*if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        Toast.makeText(getActivity(), "request the permission!!!", Toast.LENGTH_SHORT).show();
                    }else {*/
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSON_WRITE_EXTERNAL_STORAGE);
                    /*}*/
                }else {
                    dispalyGallery();
                }
                return true;
            }
        });

        tvUserSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "长按可以编辑签名哦！", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0 , 0);
                toast.show();
            }
        });

        tvUserSign.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View ADView = inflater.inflate(R.layout.alertdialog_input_user_signal, null);

                builder.setTitle("更改新签名：");
                builder.setMessage("有趣的签名，有趣的灵魂");
                builder.setView(ADView);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userSignal = etInputUserSign.getText().toString().trim();
                        userViewModel.getUser().getValue().setUserSignal(userSignal);
                        tvUserSign.setText(userSignal);
                        /*
                        User对象为可观察的Data，但是其属性值不是
                        Log.d(TAG, "onClick: userSignal = " + userViewModel.getUser().getValue().getUserSignal());
                        Log.d(TAG, "onClick: textview content = " + tvUserSign.getText().toString());*/
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                etInputUserSign = dialog.findViewById(R.id.et_input_user_sign);

                return true;
            }
        });
    }

    private void dispalyGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, PICK_IAMGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IAMGE_CODE) {
            if (resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {
                    //判断手机系统版本号，4.4及以上使用此方法处理
                    handleImageOnKitKat(data);
                } else {
                    //4.4及以下使用此方法处理
                    handleImageBeforeKitKat(data);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSON_WRITE_EXTERNAL_STORAGE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispalyGallery();
            }else {
                Toast.makeText(getActivity(), "拒绝赋予读取权限，无法更改头像", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void handleImageOnKitKat(Intent data) {
            String iamgePath = null;
            Uri uri = data.getData();
            /*Log.d(TAG, "handleImageOnKitKat: uri = " + uri);
            Log.d(TAG, "handleImageOnKitKat: uri.Scheme = " + uri.getScheme());
            Log.d(TAG, "handleImageOnKitKat: uri.Authority = " + uri.getAuthority());
            Log.d(TAG, "handleImageOnKitKat: uri.path = " + uri.getPath());
            Log.d(TAG, "handleImageOnKitKat: uri.documnetID = " + DocumentsContract.getDocumentId(uri));*/
            if (DocumentsContract.isDocumentUri(getActivity(), uri)){
                //如果是document类型的uri，则通过document id进行处理
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                    String id = docId.split(":")[1];//解析处数字格式的id
                    Log.d(TAG, "handleImageOnKitKat: docID = " + id);
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    Log.d(TAG, "handleImageOnKitKat: selection = " + selection);
                    iamgePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                    Log.d(TAG, "handleImageOnKitKat:第一个if: imagepath = " + iamgePath);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                    Uri contentUri = ContentUris.
                            withAppendedId(Uri.parse("content://downloads//public_downloads"), Long.valueOf(docId));
                    iamgePath = getImagePath(contentUri, null);
                    Log.d(TAG, "handleImageOnKitKat: 第一个elseif:imagepath = " + iamgePath);
                }else if ("content".equalsIgnoreCase(uri.getScheme())){
                    iamgePath = getImagePath(uri, null);
                    Log.d(TAG, "handleImageOnKitKat: 第二个elseif:imagepath = " + iamgePath);
                }else if ("file".equalsIgnoreCase(uri.getScheme())){
                    iamgePath = uri.getPath();
                    Log.d(TAG, "handleImageOnKitKat: 第三个elseif:imagepath = " + iamgePath);
                }
                dispalyImageView(iamgePath);//根据图片路径显示图片
            }
        }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        dispalyImageView(imagePath);
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContext().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null){
            Log.d(TAG, "getImagePath: cursor.getCount ==" + cursor.getCount());
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                Log.d(TAG, "getImagePath: Cursor->>->>::path = " + path);
            }
            cursor.close();
        }
        return path;
    }

    private void dispalyImageView(String imagePath){
        if (imagePath != null){
            Log.d(TAG, "dispalyImageView: imagePath = " + imagePath);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            Glide.with(getActivity()).load(bitmap).into(imgHeadPortrait);
        }else {
            Toast.makeText(getActivity(), "解析图片资源失败", Toast.LENGTH_SHORT).show();
        }
    }
}
