package com.example.myneteasecloudmusic.ui.main.notepage.model;

import com.example.myneteasecloudmusic.base.BaseModel;
import com.example.myneteasecloudmusic.ui.main.notepage.contract.INoteContract;
import com.example.myneteasecloudmusic.ui.main.notepage.presenter.NotePresenter;

public class NoteModel extends BaseModel<NotePresenter>implements INoteContract.Model {
    public NoteModel(NotePresenter mPresenter) {
        super(mPresenter);
    }
}
