package com.example.myneteasecloudmusic.ui.login.mvp.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.ui.login.mvp.contract.ILoginContract;
import com.example.myneteasecloudmusic.ui.login.mvp.presenter.LoginPresenter;

public class LoginModel extends BaseModel<LoginPresenter> implements ILoginContract.Model{
    public LoginModel(LoginPresenter mPresenter) {
        super(mPresenter);
    }
}
