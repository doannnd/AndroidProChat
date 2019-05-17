package com.nguyendinhdoan.androidprochat.register;

public interface RegisterView {

    void onRegisterSuccess(boolean isRegisterSuccess);

    void onRegisterFailed(String message);

    void showLoading();

    void hideLoading();
}
