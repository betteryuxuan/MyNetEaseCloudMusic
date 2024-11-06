package com.example.myneteasecloudmusic.main.mvp.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.main.mvp.contract.IMineContract;
import com.example.myneteasecloudmusic.main.mvp.presenter.MinePresenter;

public class MineModel extends BaseModel<MinePresenter> implements IMineContract.Model {

    public MineModel(MinePresenter mPresenter) {
        super(mPresenter);
    }
}
