package com.example.myneteasecloudmusic.main.mvp.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.main.mvp.contract.IFindContract;
import com.example.myneteasecloudmusic.main.mvp.model.FindModel;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.FindFragment;

public class FindPresenter extends BasePresenter<FindFragment, FindModel> implements IFindContract.Presenter {
    @Override
    public FindModel createmModel() {
        return new FindModel(this);
    }
}
