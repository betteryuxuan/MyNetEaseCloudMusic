package com.example.myneteasecloudmusic.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

public class HeadAdapter extends RecyclerView.Adapter<HeadAdapter.MyViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> list1;
    private List<String> list2;
    private List<String> urlList;

    public HeadAdapter(Context mContext) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        initList();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_first_recycleview_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(urlList.get(position))
                .into(holder.imageView);
        holder.tv1.setText(list1.get(position));
        holder.tv2.setText(list2.get(position));

        AnimationUtil.setonlyAnimateView(holder.layout);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView imageView;
        private TextView tv1;
        private TextView tv2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layout = itemView.findViewById(R.id.card_layout);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.tv1 = itemView.findViewById(R.id.tv_1);
            this.tv2 = itemView.findViewById(R.id.tv_2);
        }
    }

    private void initList() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        urlList = new ArrayList<>();
        list1.add("每日推荐");
        list1.add("心动模式");
        list1.add("私人雷达");
        list1.add("私人推荐");
        list2.add("符合你口味的好歌");
        list2.add("红心歌曲相似推荐");
        list2.add("值得反复聆听的歌");
        list2.add("多种模式随心播放");
        urlList.add("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/85cfddac-efdf-4e4a-af72-bb5ac1dfddba/depsdpv-cc4cb387-6ff0-4eb3-986d-d8c8005d95d1.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzg1Y2ZkZGFjLWVmZGYtNGU0YS1hZjcyLWJiNWFjMWRmZGRiYVwvZGVwc2Rwdi1jYzRjYjM4Ny02ZmYwLTRlYjMtOTg2ZC1kOGM4MDA1ZDk1ZDEuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.PNTEEPt8NU_P5Jmj_BZifr7cv9BLUa5qIZlb0MDBzxU");
        urlList.add("https://th.bing.com/th/id/R.ad7598aaf2dcf745163e8a1270074d50?rik=VCVVFrxnZ%2fi0mQ&riu=http%3a%2f%2fp2.music.126.net%2fAXD2wl0N3UooNRL_kPnpGQ%3d%3d%2f6650945837329535.jpg&ehk=Df6o7rsENB7WxngUIpkS8%2by2Q%2f42GSswjMvtNTMpWy4%3d&risl=&pid=ImgRaw&r=0");
        urlList.add("https://bkimg.cdn.bcebos.com/pic/80cb39dbb6fd5266d016e4587d49802bd40735fa9867?x-bce-process=image/format,f_auto/watermark,image_d2F0ZXIvYmFpa2UyNzI,g_7,xp_5,yp_5,P_20/resize,m_lfit,limit_1,h_1080");
        urlList.add("https://img0.baidu.com/it/u=3279768042,1110848362&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500");

    }
}
