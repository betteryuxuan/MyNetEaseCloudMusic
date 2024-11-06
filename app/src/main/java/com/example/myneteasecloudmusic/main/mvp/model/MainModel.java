package com.example.myneteasecloudmusic.main.mvp.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.main.mvp.contract.IMainContract;
import com.example.myneteasecloudmusic.main.mvp.presenter.MainPresenter;

public class MainModel extends BaseModel<MainPresenter> implements IMainContract.Model{
    public MainModel(MainPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void testA() {

    }
}
