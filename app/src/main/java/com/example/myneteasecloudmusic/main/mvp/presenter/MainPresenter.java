package com.example.myneteasecloudmusic.main.mvp.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.main.mvp.contract.IMainContract;
import com.example.myneteasecloudmusic.main.mvp.model.MainModel;
import com.example.myneteasecloudmusic.main.mvp.view.MainActivity;

public class MainPresenter extends BasePresenter<BaseView, MainModel> implements IMainContract.Presenter {
    @Override
    public MainModel createmModel() {
        return new MainModel(this);
    }

    @Override
    public void testC() {

    }
}
