package com.example.myneteasecloudmusic.ui.main.findpage.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseFragment;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.databinding.FragmentFindBinding;
import com.example.myneteasecloudmusic.ui.main.adapter.FindFragmentVPAdapter;
import com.example.myneteasecloudmusic.ui.main.findpage.contract.IFindContract;
import com.example.myneteasecloudmusic.ui.main.findpage.presenter.FindPresenter;
import com.example.myneteasecloudmusic.ui.search.mvp.view.SearchActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends BaseFragment<FindPresenter> implements IFindContract.View {
    private FragmentFindBinding binding;
    private List<Fragment> fragmentList;

    public FindFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
        fragmentList.add(new FindMusicFragment());
        fragmentList.add(new FindMusicFragment());
        fragmentList.add(new FindMusicFragment());
        fragmentList.add(new FindMusicFragment());
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentFindBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
        binding.findToolbarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationUtil.setAnimateView(v);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        FindFragmentVPAdapter adapter = new FindFragmentVPAdapter(this, fragmentList);
        binding.findVp.setAdapter(adapter);
        new TabLayoutMediator(binding.findTab, binding.findVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("音乐");
                        break;
                    case 1:
                        tab.setText("播客");
                        break;
                    case 2:
                        tab.setText("听书");
                        break;
                    case 3:
                        tab.setText("直播");
                        break;
                }
            }
        }).attach();

        binding.imgFindSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public FindPresenter createPresenterInstance() {
        return new FindPresenter();
    }

    @Override
    protected BaseView getMvpView() {
        return this;
    }
}