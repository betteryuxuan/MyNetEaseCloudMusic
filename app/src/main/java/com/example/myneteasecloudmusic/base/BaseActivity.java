package com.example.myneteasecloudmusic.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BaseActivity";
    public Context mContext;
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
//        Log.d(TAG,"BaseActivity");
        initView(savedInstanceState);
        initData();
        mPresenter = getmPresenterInstance();
        mPresenter.bindView(getMvpView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
    }

    // 在这初始化界面相关的，比如viewPager和fragment
    protected abstract void initView(Bundle savedInstanceState);

    // 数据
    protected abstract void initData();

    // 要求子类返回presenter
    public abstract P getmPresenterInstance();

    // 子类实现具体的View
    protected abstract BaseView getMvpView();

    public <ERROR> void responseError(ERROR error, Throwable throwable) {
        Toast.makeText(this, "貌似出现了问题", Toast.LENGTH_SHORT).show();
    }
}
