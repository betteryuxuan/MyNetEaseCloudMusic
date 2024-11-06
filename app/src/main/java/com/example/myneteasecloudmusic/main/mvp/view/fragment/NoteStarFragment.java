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
import com.example.myneteasecloudmusic.databinding.FragmentNoteBinding;
import com.example.myneteasecloudmusic.databinding.FragmentNoteStarBinding;
import com.example.myneteasecloudmusic.main.adapter.NoteStarRlvAdater;

import java.util.ArrayList;
import java.util.List;


public class NoteStarFragment extends Fragment {
    private List<String> singnerList;
    private List<Integer> singnerPicList;

    private FragmentNoteStarBinding binding;

    public NoteStarFragment() {
    }


    public static NoteStarFragment newInstance(String param1, String param2) {
        NoteStarFragment fragment = new NoteStarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singnerList = new ArrayList<>();
        singnerPicList = new ArrayList<>();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteStarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NoteStarRlvAdater adater = new NoteStarRlvAdater(getContext(), singnerList, singnerPicList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.noteStarRlv.setAdapter(adater);
        binding.noteStarRlv.setLayoutManager(layoutManager);

    }

    private void initData() {
        singnerList.add("邓紫棋");
        singnerList.add("澤野弘之");
        singnerList.add("赵雷");
        singnerList.add("BillieEilish");
        singnerList.add("Ariana...");
        singnerList.add("Ed Shee...");
        singnerList.add("TaylorS...");
        singnerPicList.add(R.drawable.pic_singner_1);
        singnerPicList.add(R.drawable.pic_singner_3);
        singnerPicList.add(R.drawable.pic_singner_4);
        singnerPicList.add(R.drawable.pic_singner_5);
        singnerPicList.add(R.drawable.pic_singner_2);
        singnerPicList.add(R.drawable.pic_singner_6);
        singnerPicList.add(R.drawable.pic_singner_7);
    }
}