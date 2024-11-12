package com.example.myneteasecloudmusic.ui.main.adapter;

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
import com.example.myneteasecloudmusic.ui.listen.ListenActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHodler> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> nameList;
    private List<String> singerList;
    private List<Integer> picList;

    public SongsAdapter(Context mContext, List<Integer> picList, List<String> nameList, List<String> singerList) {
        this.mContext = mContext;
        this.nameList = nameList;
        this.picList = picList;
        this.singerList = singerList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_second_recycleview_layout, parent, false);
        SongsAdapter.MyViewHodler myViewHolder = new MyViewHodler(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.layout1Img1.setImageResource(picList.get(0));
        holder.layout1Tv1.setText(nameList.get(0));
        holder.layout1Tv2.setText(singerList.get(0));

        holder.layout2Img1.setImageResource(picList.get(1));
        holder.layout2Tv1.setText(nameList.get(1));
        holder.layout2Tv2.setText(singerList.get(1));

        holder.layout3Img1.setImageResource(picList.get(2));
        holder.layout3Tv1.setText(nameList.get(2));
        holder.layout3Tv2.setText(singerList.get(2));


        AnimationUtil.setonlyAnimateView(holder.layout2);
        AnimationUtil.setonlyAnimateView(holder.layout3);

        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ListenActivity.class);
                mContext.startActivity(intent);
                AnimationUtil.setAnimateView(holder.layout1);
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
