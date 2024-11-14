package com.example.myneteasecloudmusic.ui.main.recommendpage.model;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.bean.SongBean;
import com.example.myneteasecloudmusic.ui.main.recommendpage.contract.IRecommendContract;
import com.example.myneteasecloudmusic.ui.main.recommendpage.presenter.RecommendPresenter;

import java.util.ArrayList;
import java.util.List;

public class RecommendModel extends BaseModel<RecommendPresenter> implements IRecommendContract.Model {


    public RecommendModel(RecommendPresenter mPresenter) {
        super(mPresenter);
    }

    public List<SongBean> getSongList1() {
        List<SongBean> songList1 = new ArrayList<>();
        songList1.add(new SongBean("APT.", "ROSÉ/Bruno Mars", R.drawable.pic_song1));
        songList1.add(new SongBean("Please Please Please", "Sabrina Carpenter", R.drawable.pic_song3));
        songList1.add(new SongBean("不为谁而作的歌", "林俊杰", R.drawable.pic_song15));
        return songList1;
    }

    public List<SongBean> getSongList2() {
        List<SongBean> songList2 = new ArrayList<>();
        songList2.add(new SongBean("虚拟", "陈粒", R.drawable.pic_song2));
        songList2.add(new SongBean("Ask me why(眞人の決意)", "久石譲", R.drawable.pic_song4));
        songList2.add(new SongBean("Sacred Play Secret Place", "Matryoshka", R.drawable.pic_song13));
        return songList2;
    }
}
