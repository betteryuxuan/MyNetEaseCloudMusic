package com.example.myneteasecloudmusic.ui.login.mvp.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.ui.login.mvp.contract.ILoginContract;
import com.example.myneteasecloudmusic.ui.login.mvp.model.LoginModel;
import com.example.myneteasecloudmusic.ui.login.mvp.view.LoginActivity;

public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel>implements ILoginContract.Presenter {
    @Override
    public LoginModel createmModel() {
        return new LoginModel(this);
    }

    @Override
    public void loginActivity() {
        mView.loginActivity();
    }
}
