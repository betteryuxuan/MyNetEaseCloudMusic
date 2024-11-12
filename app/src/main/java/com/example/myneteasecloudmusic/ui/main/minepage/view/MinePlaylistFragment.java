package com.example.myneteasecloudmusic.ui.main.minepage.view;

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
import com.example.myneteasecloudmusic.ui.main.adapter.MineSongListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MinePlaylistFragment extends Fragment {

    private List<String> nameList;
    private List<String> infoList;
    private List<Integer> picList;

    public MinePlaylistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_playlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rlv = view.findViewById(R.id.mine_song_list_rlv);

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
        nameList.add("我喜欢的音乐");
        nameList.add("Asia");
        nameList.add("Breath");
        nameList.add("California");
        nameList.add("Death");
        infoList.add("798首·1.9万次播放");
        infoList.add("575首·179次播放");
        infoList.add("336首·80次播放");
        infoList.add("113首·38次播放");
        infoList.add("52首·22次播放");
        picList.add(R.drawable.pic_song10_love);
        picList.add(R.drawable.pic_song6);
        picList.add(R.drawable.pic_song7);
        picList.add(R.drawable.pic_song8);
        picList.add(R.drawable.pic_song9);
    }
}