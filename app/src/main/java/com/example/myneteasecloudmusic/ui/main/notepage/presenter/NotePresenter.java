package com.example.myneteasecloudmusic.ui.main.notepage.presenter;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.ui.main.notepage.contract.INoteContract;
import com.example.myneteasecloudmusic.ui.main.notepage.model.NoteModel;
import com.example.myneteasecloudmusic.ui.main.notepage.view.NoteFragment;

public class NotePresenter extends BasePresenter<NoteFragment, NoteModel> implements INoteContract.Presenter {
    @Override
    public NoteModel createmModel() {
        return new NoteModel(this);
    }
}


