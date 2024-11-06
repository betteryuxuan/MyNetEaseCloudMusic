package com.example.myneteasecloudmusic.base;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    public V mView;
    public M mModel;

    public BasePresenter() {
        this.mModel = createmModel();
    }

    // view层调用绑定其与presenter
    public void bindView(V mView) {
        this.mView = mView;

    }

    public void unBindView() {
        this.mView = null;
    }

    public V getView() {
        return mView;
    }

    // 检查是否绑定了View，防止空指针
    public boolean isViewAttached() {
        return mView != null;
    }

    // model层让子类去创建对应的model实现层
    public abstract M createmModel();
}
