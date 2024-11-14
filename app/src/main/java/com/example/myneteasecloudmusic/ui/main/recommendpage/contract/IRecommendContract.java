package com.example.myneteasecloudmusic.ui.main.recommendpage.contract;

import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.bean.SongBean;

import java.util.List;

public interface IRecommendContract {

    interface View extends BaseView {
        void showTime(String time);

        void setAdapters();

        void updateSongList(List<SongBean> songList1, List<SongBean> songList2);
    }

    interface Presenter {
        void loadTime();

        void setAdapters();

        void initList();
    }

    interface Model {
        List<SongBean> getSongList1();

        List<SongBean> getSongList2();
    }
}


