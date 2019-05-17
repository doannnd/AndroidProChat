package com.nguyendinhdoan.androidprochat.login;

public interface LoginView {

    void onLoginSuccess(boolean isLoginSuccess);

    void onLoginFailed(String message);

    void showLoading();

    void hideLoading();
}
