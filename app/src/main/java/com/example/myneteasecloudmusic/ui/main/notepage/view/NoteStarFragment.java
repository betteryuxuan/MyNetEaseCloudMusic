package com.example.myneteasecloudmusic.ui.main.notepage.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.databinding.FragmentNoteStarBinding;
import com.example.myneteasecloudmusic.ui.listen.view.ListenActivity;
import com.example.myneteasecloudmusic.ui.main.adapter.NoteStarRlvAdater;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.ArrayList;
import java.util.List;


public class NoteStarFragment extends Fragment {
    private List<String> singnerList;
    private List<Integer> singnerPicList;
    private boolean isLiked = false;
    private boolean isLiked2 = false;

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
        initData();
        binding.itemNoteLayout.songCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView(binding.itemNoteLayout.songCard);
                Intent intent = new Intent(getContext(), ListenActivity.class);
                intent.putExtra("songName", binding.itemNoteLayout.songName.getText());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_bottom, 0);
            }
        });

        NoteStarRlvAdater adater = new NoteStarRlvAdater(getContext(), singnerList, singnerPicList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.noteStarRlv.setAdapter(adater);
        binding.noteStarRlv.setLayoutManager(layoutManager);

        binding.itemNoteLayout.imgNoteGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLiked) {
                    binding.itemNoteLayout.imgNoteGood.setImageResource(R.drawable.ic_good_4);
                    int currentLikes = Integer.parseInt((String) binding.itemNoteLayout.tvNoteGood.getText());
                    binding.itemNoteLayout.tvNoteGood.setText(String.valueOf(currentLikes - 1));
                    isLiked = false;
                } else {
                    AnimationUtil.setLikeAnimate(binding.itemNoteLayout.imgNoteGood);
                    binding.itemNoteLayout.imgNoteGood.setImageResource(R.drawable.ic_note_good_click);
                    int currentLikes = Integer.parseInt((String) binding.itemNoteLayout.tvNoteGood.getText());
                    binding.itemNoteLayout.tvNoteGood.setText(String.valueOf(currentLikes + 1));
                    isLiked = true;
                }
            }
        });

        binding.itemNoteLayout2.imgNoteGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLiked2) {
                    binding.itemNoteLayout2.imgNoteGood.setImageResource(R.drawable.ic_good_4);
                    int currentLikes = Integer.parseInt((String) binding.itemNoteLayout2.tvNoteGood.getText());
                    binding.itemNoteLayout2.tvNoteGood.setText(String.valueOf(currentLikes - 1));
                    isLiked2 = false;
                } else {
                    AnimationUtil.setLikeAnimate(binding.itemNoteLayout2.imgNoteGood);
                    binding.itemNoteLayout2.imgNoteGood.setImageResource(R.drawable.ic_note_good_click);
                    int currentLikes = Integer.parseInt((String) binding.itemNoteLayout2.tvNoteGood.getText());
                    binding.itemNoteLayout2.tvNoteGood.setText(String.valueOf(currentLikes + 1));
                    isLiked2 = true;
                }
            }
        });

        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout.avatar);
        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout.userName);
        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout2.avatar);
        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout2.userName);
        AnimationUtil.setonlyAnimateView(binding.noteStarCard1);
        AnimationUtil.setonlyAnimateView(binding.noteStarImg1);
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