package com.nguyendinhdoan.androidprochat.login;

import android.os.Bundle;

import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * handle login with api of QuickBlock
     * @param userName name of user
     * @param password password of user
     */
    public void performLogin(String userName, String password) {
        loginView.showLoading();
        QBUser qbUser = new QBUser(userName, password);

        QBUsers.signIn(qbUser).performAsync(
                new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        loginView.onLoginSuccess(true);
                        loginView.hideLoading();
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        loginView.onLoginFailed(e.getMessage());
                        loginView.hideLoading();
                    }
                }
        );
    }

}
