package com.example.laurenvoigt.facebooklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
    TextView txtStatus;
    LoginButton login_button;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplication());
        setContentView(R.layout.activity_main);
        initializeControls();
        loginWithFB();

    }
    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        login_button = (LoginButton)findViewById(R.id.login_button);
    }

    private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                txtStatus.setText("Login Sucessful\n" + loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Cancelled.");
            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error: " + error.getMessage());
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
