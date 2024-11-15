package com.example.myneteasecloudmusic.ui.main.mainpage.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseActivity;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.databinding.ActivityMainBinding;
import com.example.myneteasecloudmusic.ui.listen.ListenActivity;
import com.example.myneteasecloudmusic.ui.login.mvp.view.LoginActivity;
import com.example.myneteasecloudmusic.ui.main.adapter.MainFragmentVPAdapter;
import com.example.myneteasecloudmusic.ui.main.mainpage.contract.IMainContract;
import com.example.myneteasecloudmusic.ui.main.mainpage.presenter.MainPresenter;
import com.example.myneteasecloudmusic.ui.main.findpage.view.FindFragment;
import com.example.myneteasecloudmusic.ui.main.minepage.view.MineFragment;
import com.example.myneteasecloudmusic.ui.main.notepage.view.NoteFragment;
import com.example.myneteasecloudmusic.ui.main.recommendpage.view.RecommendFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.View {
    private static final String TAG = "MainActivityTag";

    private ActivityMainBinding binding;
    private List<Fragment> fragments = new ArrayList<>();

    // 在 BaseActivity 的 onCreate 被调用
    @Override
    protected void initView(Bundle savedInstanceState) {
        // 初始化ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragments.add(new RecommendFragment());
        fragments.add(new FindFragment());
        fragments.add(new NoteFragment());
        fragments.add(new MineFragment());
        MainFragmentVPAdapter adapter = new MainFragmentVPAdapter(this, fragments);
        binding.contentMain.mainViewPager.setAdapter(adapter);
        binding.contentMain.mainViewPager.setOffscreenPageLimit(4);
        binding.contentMain.mainViewPager.setCurrentItem(0);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // 在 BaseActivity 的 onCreate 被调用
    @Override
    protected void initData() {
        initBottomNavigationView();
        createBrocadcastReceiver();
    }

    @Override
    public MainPresenter getmPresenterInstance() {
        return new MainPresenter();
    }

    @Override
    protected BaseView getMvpView() {
        return this;
    }


    private void createBrocadcastReceiver() {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.myneteasecloudmusic.MUSIC_NAME");
        MainActivity.LoacalReceiver receiver = new LoacalReceiver();
        manager.registerReceiver(receiver, filter);
    }

    class LoacalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String songName = intent.getStringExtra("songName");
            Log.d(TAG, "onReceive: " + songName);
            FloatingActionButton fab = findViewById(R.id.fab_all_play);
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + songName);
                    Intent intent2 = new Intent(MainActivity.this, ListenActivity.class);
                    intent2.putExtra("songName", songName);
                    startActivity(intent2);
                    if (mContext instanceof Activity) {
                        ((Activity) mContext).overridePendingTransition(R.anim.slide_in_bottom, 0);
                    }
                }
            });
        }
    }


    private void initBottomNavigationView() {
        // viewPager 绑定 BottomNavigationView
        binding.contentMain.mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.contentMain.bottomView.getMenu().getItem(position).setChecked(true);
            }
        });

        // 禁用vp的翻动
        binding.contentMain.mainViewPager.setUserInputEnabled(false);
        // BottomNavigationView 绑定 viewPager
        binding.contentMain.bottomView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_item1) {
                    binding.contentMain.mainViewPager.setCurrentItem(0, false);
                    return true;
                } else if (itemId == R.id.navigation_item2) {
                    binding.contentMain.mainViewPager.setCurrentItem(1, false);
                    return true;
                } else if (itemId == R.id.navigation_item3) {
                    binding.contentMain.mainViewPager.setCurrentItem(2, false);
                    return true;
                } else if (itemId == R.id.navigation_item4) {
                    binding.contentMain.mainViewPager.setCurrentItem(3, false);
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}
