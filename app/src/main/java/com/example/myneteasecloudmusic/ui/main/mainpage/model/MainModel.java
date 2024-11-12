package com.example.myneteasecloudmusic.ui.main.mainpage.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.ui.main.mainpage.contract.IMainContract;
import com.example.myneteasecloudmusic.ui.main.mainpage.presenter.MainPresenter;

public class MainModel extends BaseModel<MainPresenter> implements IMainContract.Model {
    public MainModel(MainPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void testA() {

    }
}
