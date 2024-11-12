package com.example.myneteasecloudmusic.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentVPAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();

    public MainFragmentVPAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.fragmentList = fragments;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
