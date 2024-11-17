package com.example.myneteasecloudmusic.ui.main.minepage.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.databinding.FragmentMineCommunityBinding;
import com.example.myneteasecloudmusic.ui.listen.view.ListenActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

public class MineCommunityFragment extends Fragment {
    private FragmentMineCommunityBinding binding;
    private boolean isLiked = false;
    private boolean isLiked2 = false;

    public MineCommunityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMineCommunityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.itemMineLayout.songCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView(binding.itemMineLayout.songCard);
                Intent intent = new Intent(getContext(), ListenActivity.class);
                intent.putExtra("songName", binding.itemMineLayout.songName.getText());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_bottom, 0);
            }
        });


        binding.itemMineLayout.imgNoteGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLiked) {
                    binding.itemMineLayout.imgNoteGood.setImageResource(R.drawable.ic_good_4);
                    int currentLikes = Integer.parseInt((String) binding.itemMineLayout.tvNoteGood.getText());
                    binding.itemMineLayout.tvNoteGood.setText(String.valueOf(currentLikes - 1));
                    isLiked = false;
                } else {
                    AnimationUtil.setLikeAnimate(binding.itemMineLayout.imgNoteGood);
                    binding.itemMineLayout.imgNoteGood.setImageResource(R.drawable.ic_note_good_click);
                    int currentLikes = Integer.parseInt((String) binding.itemMineLayout.tvNoteGood.getText());
                    binding.itemMineLayout.tvNoteGood.setText(String.valueOf(currentLikes + 1));
                    isLiked = true;
                }
            }
        });

        binding.itemMineLayout2.imgNoteGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLiked2) {
                    binding.itemMineLayout2.imgNoteGood.setImageResource(R.drawable.ic_good_4);
                    int currentLikes = Integer.parseInt((String) binding.itemMineLayout2.tvNoteGood.getText());
                    binding.itemMineLayout2.tvNoteGood.setText(String.valueOf(currentLikes - 1));
                    isLiked2 = false;
                } else {
                    AnimationUtil.setLikeAnimate(binding.itemMineLayout2.imgNoteGood);
                    binding.itemMineLayout2.imgNoteGood.setImageResource(R.drawable.ic_note_good_click);
                    int currentLikes = Integer.parseInt((String) binding.itemMineLayout2.tvNoteGood.getText());
                    binding.itemMineLayout2.tvNoteGood.setText(String.valueOf(currentLikes + 1));
                    isLiked2 = true;
                }
            }
        });
        AnimationUtil.setonlyAnimateView(binding.itemMineLayout.songCard);
        AnimationUtil.setAnimateView(binding.itemMineLayout.avatar);
        AnimationUtil.setAnimateView(binding.itemMineLayout2.avatar);
    }
}