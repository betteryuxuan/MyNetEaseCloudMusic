package com.example.myneteasecloudmusic.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

public class MineSongListAdapter extends RecyclerView.Adapter<MineSongListAdapter.MyViewHodler> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> nameList;
    private List<String> infoList;
    private List<Integer> picList;

    public MineSongListAdapter(Context mContext, List<String> nameList, List<String> infoList, List<Integer> picList) {
        this.infoList = infoList;
        this.mContext = mContext;
        this.nameList = nameList;
        this.picList = picList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MineSongListAdapter.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_song_list_layout, parent, false);
        MineSongListAdapter.MyViewHodler myViewHolder = new MineSongListAdapter.MyViewHodler(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MineSongListAdapter.MyViewHodler holder, int position) {
        holder.img.setImageResource(picList.get(position));
        holder.tv1.setText(nameList.get(position));
        holder.tv2.setText(infoList.get(position));

        AnimationUtil.setonlyAnimateView(holder.layout1);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {

        private TextView tv1;
        private TextView tv2;
        private ImageView img;
        private ConstraintLayout layout1;


        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            layout1 = itemView.findViewById(R.id.mine_song_list_layou);
            img = itemView.findViewById(R.id.mine_song_list_img);
            tv1 = itemView.findViewById(R.id.mine_song_list_name);
            tv2 = itemView.findViewById(R.id.mine_song_list_singner);
        }
    }

}