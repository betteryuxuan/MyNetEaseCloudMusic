package com.example.myneteasecloudmusic.main.mvp.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.main.mvp.contract.IMainContract;
import com.example.myneteasecloudmusic.main.mvp.contract.IRecommendContract;
import com.example.myneteasecloudmusic.main.mvp.presenter.RecommendPresenter;

public class RecommendModel extends BaseModel<RecommendPresenter> implements IRecommendContract.Model {


    public RecommendModel(RecommendPresenter mPresenter) {
        super(mPresenter);
    }
}
