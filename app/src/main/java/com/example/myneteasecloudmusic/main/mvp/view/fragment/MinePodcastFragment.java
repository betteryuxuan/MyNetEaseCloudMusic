package com.example.myneteasecloudmusic.main.mvp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.main.adapter.MineSongListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MinePodcastFragment extends Fragment {

    private List<String> nameList;
    private List<String> infoList;
    private List<Integer> picList;

    public MinePodcastFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_podcast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rlv = view.findViewById(R.id.mine_podcast_rlv);

        initList();
        MineSongListAdapter adapter = new MineSongListAdapter(getContext(), nameList, infoList, picList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rlv.setAdapter(adapter);
        rlv.setLayoutManager(layoutManager);
    }

    private void initList() {
        nameList = new ArrayList<>();
        infoList = new ArrayList<>();
        picList = new ArrayList<>();
        nameList.add("最近更新");
        nameList.add("我点赞的声音");
        nameList.add("The Complete Ears Megamix by J...");
        nameList.add("军师联盟之大军师司马懿2虎啸龙吟");
        infoList.add("暂无更新，收藏播单查看最新单集");
        infoList.add("共9个");
        infoList.add("2023-08更新：The Complete Ears Megamix");
        infoList.add("2018-02更新：O.S.N. 心猿意马");
        picList.add(R.drawable.ic_rencent);
        picList.add(R.drawable.ic_good);
        picList.add(R.drawable.pic_song12);
        picList.add(R.drawable.pic_song11);
    }
}