package com.example.myneteasecloudmusic.ui.listen;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.databinding.ActivityListenBinding;
import com.example.myneteasecloudmusic.service.MusicService;

import java.text.SimpleDateFormat;

public class ListenActivity extends AppCompatActivity {
    private ActivityListenBinding binding;
    private MusicService musicService;
    private ServiceConnection connection;
    private Handler mHandler = new Handler();
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (connection == null) {
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    musicService = ((MusicService.MyBinder) iBinder).getService();
                    if (musicService.isPlaying()) {
                        binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_stop);
                    } else {
                        binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_play);
                    }
                    Log.d("listenTag", "绑定了服务");
                    binding.sbListen.setMax(musicService.getProgress());
                    binding.tvListenDuration.setText(musicService.getProgress());
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    musicService = null;
                    Log.d("listenTag", "解绑了服务");
                }
            };
        }

        binding.imgListenStartandstop.setOnClickListener(view -> {
            if (musicService != null) {
                musicService.playOrPauseMusic();
                if (musicService.isPlaying()) {
                    binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_stop);
                } else {
                    binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_play);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, connection, BIND_AUTO_CREATE); // 绑定服务
        Log.d("listenTag", "绑定服务");

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (connection != null) {
            unbindService(connection);
            Log.d("listenTag", "解绑服务");
            connection = null;
        }
    }
}
