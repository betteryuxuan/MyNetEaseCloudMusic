package com.example.myneteasecloudmusic.main.mvp.presenter;

import android.util.Log;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.main.mvp.contract.IRecommendContract;
import com.example.myneteasecloudmusic.main.mvp.model.RecommendModel;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.RecommendFragment;
import com.example.myneteasecloudmusic.utils.TimeUtils;

public class RecommendPresenter extends BasePresenter<RecommendFragment, RecommendModel> implements IRecommendContract.Presenter {

    @Override
    public RecommendModel createmModel() {
        return new RecommendModel(this);
    }

    @Override
    public void loadTime() {
        if (isViewAttached()) {
            Log.d("RecommendPresenterTag", "创建");
            String currentTime = TimeUtils.getTimeNormal();
            mView.showTime(currentTime);
        }
    }

    @Override
    public void setAdapters() {
        mView.setAdapters();
    }
}
