package com.example.myneteasecloudmusic.ui.main.notepage.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.databinding.FragmentNoteRecommendBinding;
import com.example.myneteasecloudmusic.ui.listen.ListenActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

public class NoteRecommendFragment extends Fragment {
    private FragmentNoteRecommendBinding binding;

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

        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout.avatar);
        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout.userName);
        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout2.avatar);
        AnimationUtil.setonlyAnimateView(binding.itemNoteLayout2.userName);

    }
}