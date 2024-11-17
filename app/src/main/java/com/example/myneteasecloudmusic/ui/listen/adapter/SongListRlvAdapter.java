package com.example.myneteasecloudmusic.ui.listen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.bean.SongBean;
import com.example.myneteasecloudmusic.ui.listen.view.ListenActivity;

import java.util.List;

public class SongListRlvAdapter extends RecyclerView.Adapter<SongListRlvAdapter.MyViewHolder> {
    private List<SongBean> songList;
    private ListenActivity mActivity;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int curPlayingPosition = -1;

    public SongListRlvAdapter(List<SongBean> mList, int curSongPosition, Context mContext, ListenActivity mActivity) {
        this.songList = mList;
        this.curPlayingPosition = curSongPosition;
        this.mActivity = mActivity;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setCurPlayingPosition(int position) {
        curPlayingPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_listen_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SongBean song = songList.get(position);

        holder.tvName.setText(song.getSongName());
        holder.tvSinger.setText(song.getSingnerName());

        if (position == curPlayingPosition ) {
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.tvSinger.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.tvName.setTextSize(16);
            holder.tvSinger.setTextSize(14);
        } else {
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.tvSinger.setTextColor(mContext.getResources().getColor(R.color.gray));
             holder.tvName.setTextSize(15);
            holder.tvSinger.setTextSize(13);
        }

        holder.layout.setOnClickListener(view -> {
            Log.d("songListenTag", song.getSongName());
            mActivity.playBySongName(song.getSongName());

            setCurPlayingPosition(position);
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView tvName;
        private TextView tvSinger;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_listen_song);
            tvName = itemView.findViewById(R.id.item_listen_songname);
            tvSinger = itemView.findViewById(R.id.item_listen_singner);
        }
    }
}
