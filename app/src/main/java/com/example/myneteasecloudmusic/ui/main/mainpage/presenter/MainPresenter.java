package com.example.myneteasecloudmusic.ui.main.mainpage.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.ui.main.mainpage.contract.IMainContract;
import com.example.myneteasecloudmusic.ui.main.mainpage.model.MainModel;

public class MainPresenter extends BasePresenter<BaseView, MainModel> implements IMainContract.Presenter {
    @Override
    public MainModel createmModel() {
        return new MainModel(this);
    }

    @Override
    public void testC() {

    }
}
