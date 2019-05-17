package com.nguyendinhdoan.androidprochat.register;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nguyendinhdoan.androidprochat.R;
import com.nguyendinhdoan.androidprochat.common.Constants;
import com.nguyendinhdoan.androidprochat.login.LoginActivity;
import com.quickblox.auth.session.QBSettings;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterView{

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private TextInputEditText registerUserNameEditText, registerPasswordEditText;
    private Button registerSignUpButton, registerCancelButton;
    private ProgressBar registerLoading;

    private RegisterPresenter registerPresenter;

    public static Intent start(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        setupUI();
        addEvents();
    }

    private void addEvents() {
        registerSignUpButton.setOnClickListener(this);
        registerCancelButton.setOnClickListener(this);
    }

    private void setupUI() {
        registerPresenter = new RegisterPresenterImpl(this);
    }

    private void initViews() {
        registerUserNameEditText = findViewById(R.id.register_user_name_edit_text);
        registerPasswordEditText = findViewById(R.id.register_password_edit_text);
        registerSignUpButton = findViewById(R.id.register_sign_up_button);
        registerCancelButton = findViewById(R.id.register_cancel_button);
        registerLoading = findViewById(R.id.register_loading);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_sign_up_button: {
                handleRegister();
                break;
            }
            case R.id.register_cancel_button: {
                launchLoginScreen();
                break;
            }
        }
    }

    private void launchLoginScreen() {
        Intent loginIntent = LoginActivity.start(this);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    private void handleRegister() {
        String userName = registerUserNameEditText.getText().toString();
        String password = registerPasswordEditText.getText().toString();

        registerPresenter.performRegister(userName, password);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void onRegisterSuccess(boolean isRegisterSuccess) {
        if (isRegisterSuccess) {
            Toast.makeText(this, "register success", Toast.LENGTH_SHORT).show();
            // open login screen
            launchLoginScreen();
        }
    }

    @Override
    public void onRegisterFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        registerLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        registerLoading.setVisibility(View.GONE);
    }
}
