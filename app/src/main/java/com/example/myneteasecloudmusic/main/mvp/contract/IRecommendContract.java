package com.example.myneteasecloudmusic.main.mvp.contract;

import com.example.myneteasecloudmusic.base.BaseView;

public interface IRecommendContract {

    interface View extends BaseView {
        void showTime(String time);

        void setAdapters();
    }

    interface Presenter {
        void loadTime();
        void setAdapters();
    }

    interface Model {
    }

}
