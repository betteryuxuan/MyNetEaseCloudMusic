package com.example.myneteasecloudmusic.ui.main.minepage.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.ui.main.minepage.presenter.MinePresenter;
import com.example.myneteasecloudmusic.ui.main.minepage.contract.IMineContract;

public class MineModel extends BaseModel<MinePresenter> implements IMineContract.Model {

    public MineModel(MinePresenter mPresenter) {
        super(mPresenter);
    }
}
