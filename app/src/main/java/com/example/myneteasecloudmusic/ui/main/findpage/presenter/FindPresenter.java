package com.example.myneteasecloudmusic.ui.main.findpage.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.ui.main.findpage.contract.IFindContract;
import com.example.myneteasecloudmusic.ui.main.findpage.view.FindFragment;
import com.example.myneteasecloudmusic.ui.main.findpage.model.FindModel;

public class FindPresenter extends BasePresenter<FindFragment, FindModel> implements IFindContract.Presenter {
    @Override
    public FindModel createmModel() {
        return new FindModel(this);
    }
}
