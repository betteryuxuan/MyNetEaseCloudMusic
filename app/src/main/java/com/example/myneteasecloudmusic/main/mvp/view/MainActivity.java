package com.example.myneteasecloudmusic.main.mvp.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseActivity;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.databinding.ActivityMainBinding;
import com.example.myneteasecloudmusic.main.adapter.MainFragmentVPAdapter;
import com.example.myneteasecloudmusic.main.mvp.contract.IMainContract;
import com.example.myneteasecloudmusic.main.mvp.presenter.MainPresenter;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.FindFragment;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.MineFragment;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.NoteFragment;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.RecommendFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.View {
    private static final String TAG = "MainActivityTag";

    private ActivityMainBinding binding;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

        fragments.add(RecommendFragment.newInstance("1", "2"));
        fragments.add(new FindFragment());
        fragments.add(NoteFragment.newInstance("3", "2"));
        fragments.add(MineFragment.newInstance("4", "2"));
        MainFragmentVPAdapter adapter = new MainFragmentVPAdapter(this, fragments);
        binding.contentMain.mainViewPager.setAdapter(adapter);
        binding.contentMain.mainViewPager.setOffscreenPageLimit(4);

        binding.contentMain.mainViewPager.setCurrentItem(1);
    }

    @Override
    public MainPresenter getmPresenterInstance() {
        return new MainPresenter();
    }

    @Override
    protected BaseView getMvpView() {
        return this;
    }

    // 在 BaseActivity 的 onCreate 被调用
    @Override
    protected void initData() {
        initBottomNavigationView();
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
    public void onClick(View v) {

    }


    @Override
    public void testB() {

    }
}
