package com.nguyendinhdoan.androidprochat.login;

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
import com.nguyendinhdoan.androidprochat.chat.ChatActivity;
import com.nguyendinhdoan.androidprochat.common.Constants;
import com.nguyendinhdoan.androidprochat.register.RegisterActivity;
import com.quickblox.auth.session.QBSettings;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView{

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button loginButton, registerButton;
    private TextInputEditText userNameEditText, passwordEditText;
    private ProgressBar loginLoading;

    private LoginPresenter loginPresenter;

    public static Intent start(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setupUI();
        addEvents();
    }

    private void setupUI() {
        initQuickBlock();

        loginPresenter = new LoginPresenterImpl(this);
    }

    private void initQuickBlock() {
        QBSettings.getInstance().init(
                this,
                Constants.APPLICATION_ID,
                Constants.AUTHORIZATION_KEY,
                Constants.AUTHORIZATION_SECRET);

        QBSettings.getInstance().setAccountKey(Constants.ACCOUNT_KEY);
    }

    private void addEvents() {
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    private void initViews() {
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        userNameEditText = findViewById(R.id.user_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginLoading = findViewById(R.id.login_loading);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button: {
                handleLogin();
                break;
            }
            case R.id.register_button: {
                launchRegisterScreen();
                break;
            }
        }
    }

    private void handleLogin() {
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        loginPresenter.performLogin(userName, password);
    }

    private void launchRegisterScreen() {
        Intent registerIntent = RegisterActivity.start(this);
        registerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(registerIntent);
        finish();
    }

    @Override
    public void onLoginSuccess(String userName, String password) {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
        launchChatScreen(userName, password);
    }

    private void launchChatScreen(String userName, String password) {
        Intent chatIntent = ChatActivity.start(this);

        chatIntent.putExtra(Constants.USER_NAME_KEY, userName);
        chatIntent.putExtra(Constants.PASSWORD_KEY, password);
        chatIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(chatIntent);
        finish();
    }

    @Override
    public void onLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        loginLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loginLoading.setVisibility(View.GONE);
    }
}
