package com.example.myneteasecloudmusic.ui.listen.fragment;

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
import com.example.myneteasecloudmusic.bean.SongBean;
import com.example.myneteasecloudmusic.ui.listen.adapter.SongListRlvAdapter;
import com.example.myneteasecloudmusic.ui.listen.view.ListenActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class SongListBottomSheetFragment extends BottomSheetDialogFragment {

    private List<SongBean> songList;
    private int curSongPosition;

    public SongListBottomSheetFragment(List<SongBean> songList, int curSongPosition) {
        this.songList = songList;
        this.curSongPosition = curSongPosition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_song_list_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rlv = view.findViewById(R.id.rlv_listen_songlist);
        SongListRlvAdapter adapter = new SongListRlvAdapter(songList,curSongPosition, getContext(), (ListenActivity) getActivity());
        rlv.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rlv.setLayoutManager(manager);

    }
}