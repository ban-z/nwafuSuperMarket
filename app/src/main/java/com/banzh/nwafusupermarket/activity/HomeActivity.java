package com.banzh.nwafusupermarket.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.banzh.nwafusupermarket.BaseActivity;
import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.data.User;
import com.banzh.nwafusupermarket.databinding.ActivityHomeBinding;
import com.banzh.nwafusupermarket.viewmodel.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends BaseActivity {

    private static String TAG = "HomeActivity";

    ActivityHomeBinding homeBinding;
    BottomNavigationView bottomNavigationView;
    NavController navController;

    private UserViewModel userViewModel;
    private String userName;
    private String password;
    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentInformation();
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initInforamtion();
        initView();
    }

    private void getIntentInformation(){
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        Token = intent.getStringExtra("Token");
        //Log.d(TAG, "initInforamtion: intent get message: " + "userName = " + userName + " && password =" + password + " && Token = " + Token);
    }

    private void initInforamtion() {


        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        homeBinding.setUserViewModel(userViewModel);
        homeBinding.setLifecycleOwner(this);
        //postValue回调的是主线程,setValue则是当前线程
        userViewModel.getUser().setValue(new User(userName, password, Token));
    }

    private void initView() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNavigationView = homeBinding.navigationBottom;

        //将BottomNavigationView与NavController绑定，实现Fragment的切换
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }
}
