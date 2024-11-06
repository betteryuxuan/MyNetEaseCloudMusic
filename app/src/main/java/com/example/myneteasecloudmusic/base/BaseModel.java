package com.example.myneteasecloudmusic.base;

public abstract class BaseModel<P extends BasePresenter> {
    public P mPresenter;

    public BaseModel(P mPresenter) {
        this.mPresenter = mPresenter;
    }
}
