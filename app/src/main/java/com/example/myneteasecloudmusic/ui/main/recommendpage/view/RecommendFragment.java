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
import com.example.myneteasecloudmusic.databinding.FragmentRecommendBinding;
import com.example.myneteasecloudmusic.ui.main.adapter.HeadAdapter;
import com.example.myneteasecloudmusic.ui.main.adapter.SongsAdapter;
import com.example.myneteasecloudmusic.ui.main.recommendpage.contract.IRecommendContract;
import com.example.myneteasecloudmusic.ui.main.recommendpage.presenter.RecommendPresenter;
import com.example.myneteasecloudmusic.ui.search.mvp.view.SearchActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements IRecommendContract.View {
    private static final String TAG = "RecommendFragment";

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

        HeadAdapter headAdapter = new HeadAdapter(getContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvRecommend1.setAdapter(headAdapter);
        binding.rvRecommend1.setLayoutManager(layoutManager1);

        initList1();
        SongsAdapter songsAdapter = new SongsAdapter(getContext(), picList, nameList, singerList);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvRecommend2.setAdapter(songsAdapter);
        binding.rvRecommend2.setLayoutManager(layoutManager2);

        initList2();
        SongsAdapter songsAdapter2 = new SongsAdapter(getContext(), picList, nameList, singerList);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvRecommend3.setAdapter(songsAdapter2);
        binding.rvRecommend3.setLayoutManager(layoutManager3);

        PagerSnapHelper snapHelper1 = new PagerSnapHelper();
        PagerSnapHelper snapHelper2 = new PagerSnapHelper();
        snapHelper1.attachToRecyclerView(binding.rvRecommend2);
        snapHelper2.attachToRecyclerView(binding.rvRecommend3);
    }

    private List<String> nameList;
    private List<String> singerList;
    private List<Integer> picList;

    private void initList1() {
        nameList = new ArrayList<>();
        singerList = new ArrayList<>();
        picList = new ArrayList<>();
        nameList.add("APT.");
        nameList.add("Please Please Please");
        nameList.add("不为谁而作的歌");
        singerList.add("ROSÉ/Bruno Mars");
        singerList.add("Sabrina Carpenter");
        singerList.add("林俊杰");
        picList.add(R.drawable.pic_song1);
        picList.add(R.drawable.pic_song3);
        picList.add(R.drawable.pic_song15);
    }

    private void initList2() {
        nameList = new ArrayList<>();
        singerList = new ArrayList<>();
        picList = new ArrayList<>();
        nameList.add("虚拟");
        nameList.add("Ask me why(眞人の決意)");
        nameList.add("Sacred Play Secret Place");
        singerList.add("陈粒");
        singerList.add("久石譲");
        singerList.add("Matryoshka");
        picList.add(R.drawable.pic_song2);
        picList.add(R.drawable.pic_song4);
        picList.add(R.drawable.pic_song13);
    }
}