package com.example.myneteasecloudmusic.main.mvp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseFragment;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.databinding.FragmentMineBinding;
import com.example.myneteasecloudmusic.main.adapter.MineFragmentVPAdapter;
import com.example.myneteasecloudmusic.main.mvp.contract.IMineContract;
import com.example.myneteasecloudmusic.main.mvp.presenter.MinePresenter;
import com.example.myneteasecloudmusic.utils.AnimationUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MineFragment extends BaseFragment<MinePresenter> implements IMineContract.View {
    private static final String TAG = "RecommendFragment";

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentMineBinding binding;

    public MineFragment() {
    }

    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragments.add(new MinePlaylistFragment());
        fragments.add(new MinePodcastFragment());
        fragments.add(new MineCommunityFragment());
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMineBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
        binding.mineToolbarMenu.setOnClickListener(new View.OnClickListener() {
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

        MineFragmentVPAdapter adapter = new MineFragmentVPAdapter(this, fragments);
        binding.vpMine.setAdapter(adapter);
//        binding.vpMine.setSaveEnabled(false);
        binding.vpMine.setUserInputEnabled(true);
        binding.vpMine.setOffscreenPageLimit(3);
        new TabLayoutMediator(binding.tabMine, binding.vpMine, new TabLayoutMediator.TabConfigurationStrategy() {
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
                        tab.setText("动态");
                        break;
                }
            }
        }).attach();

        binding.mineScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // 这里的 scrollY 是滚动的垂直偏移量
                if (scrollY > 600) {
                    binding.mineTopName.setVisibility(View.VISIBLE);
                    binding.mineTopUserImg.setVisibility(View.VISIBLE);
                } else {
                    binding.mineTopName.setVisibility(View.INVISIBLE);
                    binding.mineTopUserImg.setVisibility(View.INVISIBLE);
                }
            }
        });

        AnimationUtil.setonlyAnimateView(binding.mineCard1);
        AnimationUtil.setonlyAnimateView(binding.mineCard2);
        AnimationUtil.setonlyAnimateView(binding.mineCard3);
        AnimationUtil.setonlyAnimateView(binding.mineCard4);
        AnimationUtil.setonlyAnimateView(binding.mineCard5);
        AnimationUtil.setonlyAnimateView(binding.tvMineName);
        AnimationUtil.setonlyAnimateView(binding.mineSearch);
        AnimationUtil.setonlyAnimateView(binding.mineMore);
        AnimationUtil.setonlyAnimateView(binding.imgMineSvip);
        AnimationUtil.setonlyAnimateView(binding.imgMineUser);
    }

    @Override
    public MinePresenter createPresenterInstance() {
        return new MinePresenter();
    }

    @Override
    protected BaseView getMvpView() {
        return this;
    }
}