package com.example.myneteasecloudmusic.main.mvp.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.main.mvp.contract.IMineContract;
import com.example.myneteasecloudmusic.main.mvp.model.MineModel;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.MineFragment;

public class MinePresenter extends BasePresenter<MineFragment, MineModel> implements IMineContract.Presenter {

    @Override
    public MineModel createmModel() {
        return new MineModel(this);
    }
}
