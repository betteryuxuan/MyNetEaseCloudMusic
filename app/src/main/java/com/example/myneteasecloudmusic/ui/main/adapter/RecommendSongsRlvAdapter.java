package com.example.myneteasecloudmusic.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.bean.SongBean;
import com.example.myneteasecloudmusic.ui.listen.view.ListenActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.List;

public class RecommendSongsRlvAdapter extends RecyclerView.Adapter<RecommendSongsRlvAdapter.MyViewHodler> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<SongBean> songList;

    public RecommendSongsRlvAdapter(Context mContext, List<SongBean> songList) {
        this.mContext = mContext;
        this.songList = songList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_second_recycleview_layout, parent, false);
        RecommendSongsRlvAdapter.MyViewHodler myViewHolder = new MyViewHodler(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.layout1Img1.setImageResource(songList.get(0).getPicture());
        holder.layout1Tv1.setText(songList.get(0).getSongName());
        holder.layout1Tv2.setText(songList.get(0).getSingnerName());

        holder.layout2Img1.setImageResource(songList.get(1).getPicture());
        holder.layout2Tv1.setText(songList.get(1).getSongName());
        holder.layout2Tv2.setText(songList.get(1).getSingnerName());

        holder.layout3Img1.setImageResource(songList.get(2).getPicture());
        holder.layout3Tv1.setText(songList.get(2).getSongName());
        holder.layout3Tv2.setText(songList.get(2).getSingnerName());


        AnimationUtil.setonlyAnimateView(holder.layout2);
        AnimationUtil.setonlyAnimateView(holder.layout3);

        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView(holder.layout1);
                Intent intent = new Intent(mContext, ListenActivity.class);
                intent.putExtra("songName", holder.layout1Tv1.getText());
                mContext.startActivity(intent);
                if (mContext instanceof Activity) {
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_bottom, 0);
                }
            }
        });
        holder.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView(holder.layout2);
                Intent intent = new Intent(mContext, ListenActivity.class);
                intent.putExtra("songName", holder.layout2Tv1.getText());
                mContext.startActivity(intent);
                if (mContext instanceof Activity) {
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_bottom, 0);
                }
            }
        });
        holder.layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView(holder.layout3);
                Intent intent = new Intent(mContext, ListenActivity.class);
                intent.putExtra("songName", holder.layout3Tv1.getText());
                mContext.startActivity(intent);
                if (mContext instanceof Activity) {
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_bottom, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class MyViewHodler extends RecyclerView.ViewHolder {

        private TextView layout1Tv1;
        private TextView layout1Tv2;
        private ImageView layout1Img1;
        private ConstraintLayout layout1;

        private TextView layout2Tv1;
        private TextView layout2Tv2;
        private ImageView layout2Img1;
        private ConstraintLayout layout2;

        private TextView layout3Tv1;
        private TextView layout3Tv2;
        private ImageView layout3Img1;
        private ConstraintLayout layout3;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            layout1 = itemView.findViewById(R.id.include_1);
            layout1Img1 = layout1.findViewById(R.id.img_song);
            layout1Tv1 = layout1.findViewById(R.id.tv_name);
            layout1Tv2 = layout1.findViewById(R.id.tv_singner);

            layout2 = itemView.findViewById(R.id.include_2);
            layout2Img1 = layout2.findViewById(R.id.img_song);
            layout2Tv1 = layout2.findViewById(R.id.tv_name);
            layout2Tv2 = layout2.findViewById(R.id.tv_singner);

            layout3 = itemView.findViewById(R.id.include_3);
            layout3Img1 = layout3.findViewById(R.id.img_song);
            layout3Tv1 = layout3.findViewById(R.id.tv_name);
            layout3Tv2 = layout3.findViewById(R.id.tv_singner);
        }
    }
}
