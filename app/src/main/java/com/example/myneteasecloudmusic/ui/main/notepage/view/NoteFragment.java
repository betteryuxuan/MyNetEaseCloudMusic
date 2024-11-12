package com.example.myneteasecloudmusic.ui.main.notepage.view;

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
import com.example.myneteasecloudmusic.databinding.FragmentNoteBinding;
import com.example.myneteasecloudmusic.ui.main.adapter.NoteFragmentVPAdapter;
import com.example.myneteasecloudmusic.ui.main.notepage.contract.INoteContract;
import com.example.myneteasecloudmusic.ui.main.notepage.presenter.NotePresenter;
import com.example.myneteasecloudmusic.utils.AnimationUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends BaseFragment<NotePresenter>implements INoteContract.View {
    private FragmentNoteBinding binding;
    private List<Fragment> fragments = new ArrayList<>();

    public NoteFragment() {
    }

    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments.add(new NoteStarFragment());
        fragments.add(new NoteRecommendFragment());
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
        binding.noteToolbarMenu.setOnClickListener(new View.OnClickListener() {
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

        NoteFragmentVPAdapter vpAdapter = new NoteFragmentVPAdapter(this, fragments);
        binding.noteVp.setAdapter(vpAdapter);
        new TabLayoutMediator(binding.noteTab, binding.noteVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("关注");
                        break;
                    case 1:
                        tab.setText("推荐");
                        break;
                }
            }
        }).attach();
    }

    @Override
    public NotePresenter createPresenterInstance() {
        return new NotePresenter();
    }

    @Override
    protected BaseView getMvpView() {
        return this;
    }

}