package com.example.myneteasecloudmusic.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.List;

public class NoteStarRlvAdater extends RecyclerView.Adapter<NoteStarRlvAdater.MyViewHodler> {
    private List<String> singnerList;
    private List<Integer> singnerPicList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public NoteStarRlvAdater(Context mContext, List<String> singnerList, List<Integer> singnerPicList) {
        this.mContext = mContext;
        this.singnerList = singnerList;
        this.singnerPicList = singnerPicList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_note_recycleview_layout, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.img.setImageResource(singnerPicList.get(position));
        holder.tv.setText(singnerList.get(position));
    }

    @Override
    public int getItemCount() {
        return singnerList.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView img;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.note_star_rlv_img);
            tv = itemView.findViewById(R.id.note_star_rlv_tv);
        }
    }
}
