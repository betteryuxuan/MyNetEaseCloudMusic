package com.example.myneteasecloudmusic.ui.main.recommendpage.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseFragment;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.bean.SongBean;
import com.example.myneteasecloudmusic.databinding.FragmentRecommendBinding;
import com.example.myneteasecloudmusic.ui.main.adapter.RecommendHeadRlvAdapter;
import com.example.myneteasecloudmusic.ui.main.adapter.RecommendSongsRlvAdapter;
import com.example.myneteasecloudmusic.ui.main.recommendpage.contract.IRecommendContract;
import com.example.myneteasecloudmusic.ui.main.recommendpage.presenter.RecommendPresenter;
import com.example.myneteasecloudmusic.ui.search.mvp.view.SearchActivity;
import com.example.myneteasecloudmusic.ui.webActivity.WebActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.List;

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements IRecommendContract.View {
    private static final String TAG = "RecommendFragment";

    private List<SongBean> songList1;
    private List<SongBean> songList2;
    private FragmentRecommendBinding binding;

    public RecommendFragment() {
    }

    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unBindView(); // 解绑 View
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentRecommendBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // onViewCreated
    @Override
    protected void initData() {
        // 绑定presenter
        mPresenter.bindView(this);

        mPresenter.loadTime();
        mPresenter.initList();
        mPresenter.setAdapters();


        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
        binding.mainToolbarMenu.setOnClickListener(new View.OnClickListener() {
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

        binding.mainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                drawerLayout.closeDrawer(GravityCompat.START);
//                Toast.makeText(requireActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });

        binding.imgRecommend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url", "https://www.wenjuan.com/s/BZjueu5/");
                startActivity(intent);
            }
        });

        binding.rvRecommend1.setNestedScrollingEnabled(false);
        binding.rvRecommend2.setNestedScrollingEnabled(false);
        binding.rvRecommend3.setNestedScrollingEnabled(false);
    }

    @Override
    public RecommendPresenter createPresenterInstance() {
        return new RecommendPresenter();
    }

    @Override
    protected BaseView getMvpView() {
        return this;
    }

    @Override
    public void showTime(String time) {
        if (time.equals("深夜")) {
            binding.tvRecommend1.setText("夜深了");
        } else {
            binding.tvRecommend1.setText(time + "好");
        }
    }

    // 上面三个 recycleView 的设置
    @Override
    public void setAdapters() {
        RecommendHeadRlvAdapter recommendHeadRlvAdapter = new RecommendHeadRlvAdapter(getContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvRecommend1.setAdapter(recommendHeadRlvAdapter);
        binding.rvRecommend1.setLayoutManager(layoutManager1);

        RecommendSongsRlvAdapter recommendSongsRlvAdapter1 = new RecommendSongsRlvAdapter(getContext(), songList1);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvRecommend2.setAdapter(recommendSongsRlvAdapter1);
        binding.rvRecommend2.setLayoutManager(layoutManager2);

        RecommendSongsRlvAdapter recommendSongsRlvAdapter2 = new RecommendSongsRlvAdapter(getContext(), songList2);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvRecommend3.setAdapter(recommendSongsRlvAdapter2);
        binding.rvRecommend3.setLayoutManager(layoutManager3);

        PagerSnapHelper snapHelper1 = new PagerSnapHelper();
        PagerSnapHelper snapHelper2 = new PagerSnapHelper();
        snapHelper1.attachToRecyclerView(binding.rvRecommend2);
        snapHelper2.attachToRecyclerView(binding.rvRecommend3);
    }

    @Override
    public void updateSongList(List<SongBean> songList1, List<SongBean> songList2) {
        this.songList1 = songList1;
        this.songList2 = songList2;
    }


}