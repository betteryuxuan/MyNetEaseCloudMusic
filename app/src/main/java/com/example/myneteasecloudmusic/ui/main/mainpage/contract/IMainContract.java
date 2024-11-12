package com.example.myneteasecloudmusic.ui.main.mainpage.contract;

import com.example.myneteasecloudmusic.base.BaseView;

public interface IMainContract {
    interface Model {
        void testA();
    }

    interface View extends BaseView {
        void testB();
    }

    interface Presenter {
        void testC();
    }
}
