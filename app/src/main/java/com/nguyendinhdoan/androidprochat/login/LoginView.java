package com.nguyendinhdoan.androidprochat.login;

public interface LoginView {

    void onLoginSuccess(String userName, String password);

    void onLoginFailed(String message);

    void showLoading();

    void hideLoading();
}
