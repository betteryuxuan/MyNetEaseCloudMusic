package com.example.myneteasecloudmusic.ui.main.minepage.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.ui.main.minepage.view.MineFragment;
import com.example.myneteasecloudmusic.ui.main.minepage.contract.IMineContract;
import com.example.myneteasecloudmusic.ui.main.minepage.model.MineModel;

public class MinePresenter extends BasePresenter<MineFragment, MineModel> implements IMineContract.Presenter {

    @Override
    public MineModel createmModel() {
        return new MineModel(this);
    }
}
