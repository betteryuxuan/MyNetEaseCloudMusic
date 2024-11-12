package com.example.myneteasecloudmusic.ui.main.recommendpage.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.ui.main.recommendpage.contract.IRecommendContract;
import com.example.myneteasecloudmusic.ui.main.recommendpage.presenter.RecommendPresenter;

public class RecommendModel extends BaseModel<RecommendPresenter> implements IRecommendContract.Model {


    public RecommendModel(RecommendPresenter mPresenter) {
        super(mPresenter);
    }
}
