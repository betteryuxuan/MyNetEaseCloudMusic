package com.example.myneteasecloudmusic.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected Context mContext;
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        return initView(inflater, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenterInstance();
        mPresenter.bindView(getMvpView());
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unBindView();
    }

    // 初始化布局，子类需要实现
    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    // 初始化数据
    protected abstract void initData();

    // 子类实现具体的Presenter
    public abstract P createPresenterInstance();

    // 子类实现具体的View
    protected abstract BaseView getMvpView();

    // 获取当前的 Presenter 实例
    public P getPresenter() {
        return mPresenter;
    }

}
