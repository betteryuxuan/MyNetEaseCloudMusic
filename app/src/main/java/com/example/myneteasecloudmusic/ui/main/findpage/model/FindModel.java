package com.example.myneteasecloudmusic.ui.main.findpage.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.ui.main.findpage.contract.IFindContract;
import com.example.myneteasecloudmusic.ui.main.findpage.presenter.FindPresenter;

public class FindModel extends BaseModel<FindPresenter>implements IFindContract.Model {
    public FindModel(FindPresenter mPresenter) {
        super(mPresenter);
    }
}
