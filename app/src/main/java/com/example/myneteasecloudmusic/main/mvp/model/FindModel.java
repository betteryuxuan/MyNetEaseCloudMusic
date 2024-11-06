package com.example.myneteasecloudmusic.main.mvp.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.main.mvp.contract.IFindContract;
import com.example.myneteasecloudmusic.main.mvp.presenter.FindPresenter;

public class FindModel extends BaseModel<FindPresenter>implements IFindContract.Model {
    public FindModel(FindPresenter mPresenter) {
        super(mPresenter);
    }
}
