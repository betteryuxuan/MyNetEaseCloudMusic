package com.example.myneteasecloudmusic.ui.main.minepage.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseFragment;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.databinding.FragmentMineBinding;
import com.example.myneteasecloudmusic.ui.main.adapter.MineFragmentVPAdapter;
import com.example.myneteasecloudmusic.ui.main.minepage.contract.IMineContract;
import com.example.myneteasecloudmusic.ui.main.minepage.presenter.MinePresenter;
import com.example.myneteasecloudmusic.ui.search.mvp.view.SearchActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;
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
        binding.tabMine.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ("动态".equals(tab.getText())) {
                    binding.tabMine.setBackground(getResources().getDrawable(R.drawable.tab_background2));
                    binding.tabMine.setTabTextColors(
                            getResources().getColor(R.color.gray),
                            getResources().getColor(R.color.black)
                    );
                } else {
                    binding.tabMine.setBackground(getResources().getDrawable(R.drawable.tab_background));
                    binding.tabMine.setTabTextColors(
                            getResources().getColor(R.color.dark_gray),
                            getResources().getColor(R.color.white_gray)
                    );
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.mineScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 600) {
                    binding.mineTopName.setVisibility(View.VISIBLE);
                    binding.mineTopUserImg.setVisibility(View.VISIBLE);
                    if (binding.vpMine.getCurrentItem() == 2) {
                        binding.tabMine.setBackgroundResource(R.color.dark_white);
                    }
                } else {
                    binding.mineTopName.setVisibility(View.INVISIBLE);
                    binding.mineTopUserImg.setVisibility(View.INVISIBLE);
                    if (binding.vpMine.getCurrentItem() == 2) {
                        binding.tabMine.setBackground(getResources().getDrawable(R.drawable.tab_background2));
                    }
                }
            }
        });

        binding.imgMineSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView(binding.imgMineSearch);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        setAnimation();
    }

    private void setAnimation() {
        AnimationUtil.setonlyAnimateView(binding.mineCard1);
        AnimationUtil.setonlyAnimateView(binding.mineCard2);
        AnimationUtil.setonlyAnimateView(binding.mineCard3);
        AnimationUtil.setonlyAnimateView(binding.mineCard4);
        AnimationUtil.setonlyAnimateView(binding.mineCard5);
        AnimationUtil.setonlyAnimateView(binding.tvMineName);
        AnimationUtil.setonlyAnimateView(binding.imgMineMore);
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