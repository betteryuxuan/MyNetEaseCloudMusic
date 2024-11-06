package com.example.myneteasecloudmusic.main.mvp.presenter;

import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;

import com.example.myneteasecloudmusic.base.BasePresenter;
import com.example.myneteasecloudmusic.main.mvp.contract.INoteContract;
import com.example.myneteasecloudmusic.main.mvp.model.NoteModel;
import com.example.myneteasecloudmusic.main.mvp.view.fragment.NoteFragment;

import java.util.ArrayList;

public class NotePresenter extends BasePresenter<NoteFragment, NoteModel> implements INoteContract.Presenter {
    @Override
    public NoteModel createmModel() {
        return new NoteModel(this);
    }
}


