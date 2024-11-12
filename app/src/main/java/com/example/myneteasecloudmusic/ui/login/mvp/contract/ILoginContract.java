package com.example.myneteasecloudmusic.ui.login.mvp.contract;

import com.example.myneteasecloudmusic.base.BaseView;

public interface ILoginContract {
    interface Model {
    }

    interface View extends BaseView {
        public void loginActivity();
    }

    interface Presenter {
        public void loginActivity();
    }
}
