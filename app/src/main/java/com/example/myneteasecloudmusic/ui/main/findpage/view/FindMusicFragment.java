package com.example.myneteasecloudmusic.ui.main.findpage.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myneteasecloudmusic.R;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class FindMusicFragment extends Fragment {
    private List<Integer> bannerInfoList;

    public FindMusicFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Banner banner = view.findViewById(R.id.find_music_banner);
        bannerInfoList = new ArrayList<>();
        bannerInfoList.add(R.drawable.pic_banner_2);
        bannerInfoList.add(R.drawable.pic_banner_3);
        bannerInfoList.add(R.drawable.pic_banner_4);
        bannerInfoList.add(R.drawable.pic_banner_1);

        banner.setAdapter(new BannerImageAdapter<Integer>(bannerInfoList) {
                    @Override
                    public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                        holder.imageView.setImageResource(bannerInfoList.get(position));
                        Glide.with(getContext())
                                .load(bannerInfoList.get(position))
                                .fitCenter()
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(getContext()))
                .setIndicatorGravity(IndicatorConfig.Direction.LEFT)
                .setIndicatorNormalColorRes(R.color.gray_transparent)
                .setIndicatorSelectedColorRes(R.color.white)
                .setLoopTime(4000);
    }
}