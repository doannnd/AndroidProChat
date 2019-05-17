package com.nguyendinhdoan.androidprochat.register;

import android.os.Bundle;

import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView registerView;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
    }

    /**
     *
     * @param userName is name of user enter
     * @param password is password of user enter
     */
    @Override
    public void performRegister(String fullName, String userName, String password) {
        registerView.showLoading();
        QBUser qbUser = new QBUser(userName, password);
        qbUser.setFullName(fullName);

        QBUsers.signUp(qbUser).performAsync(
                new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        registerView.onRegisterSuccess(true);
                        registerView.hideLoading();
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        registerView.onRegisterFailed(e.getMessage());
                        registerView.hideLoading();
                    }
                }
        );
    }


}
