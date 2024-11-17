package com.example.myneteasecloudmusic.ui.main.notepage.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.databinding.FragmentNoteRecommendBinding;
import com.example.myneteasecloudmusic.ui.listen.view.ListenActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

public class NoteRecommendFragment extends Fragment {
    private FragmentNoteRecommendBinding binding;
    private boolean isLiked = false;
    private boolean isLiked2 = false;

    public NoteRecommendFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteRecommendBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    }
}