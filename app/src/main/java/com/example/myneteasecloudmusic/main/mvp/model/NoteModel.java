package com.example.myneteasecloudmusic.main.mvp.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.main.mvp.contract.INoteContract;
import com.example.myneteasecloudmusic.main.mvp.presenter.NotePresenter;

public class NoteModel extends BaseModel<NotePresenter>implements INoteContract.Model {
    public NoteModel(NotePresenter mPresenter) {
        super(mPresenter);
    }
}
