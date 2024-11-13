package com.example.myneteasecloudmusic.ui.listen;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.databinding.ActivityListenBinding;
import com.example.myneteasecloudmusic.service.MusicService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListenActivity extends AppCompatActivity {
    private ActivityListenBinding binding;
    private MusicService musicService;
    private boolean isBound = false;
    private int curSongIndex;
    private ObjectAnimator rotationAnimator;
    private String songName;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.MusicBinder musicBinder = (MusicService.MusicBinder) iBinder;
            musicService = musicBinder.getService();
            isBound = true;

            String totalDuration = formatTime(musicService.getTotalDuration());
            Log.d("listenTag", "totalDuration: " + totalDuration);
            binding.tvListenDuration.setText(totalDuration);
            binding.sbListen.setMax(musicService.getTotalDuration());

            updateProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent getIntent = getIntent();
        songName = getIntent.getStringExtra("songName");
        Log.d("listenTag", songName);
        initList();
        dealWithSongName();

        rotationAnimator = ObjectAnimator.ofFloat(binding.imgListenSong, "rotation", 0f, 360f);
        rotationAnimator.setDuration(50000); // 设置动画持续时间，单位毫秒
        rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 无限循环
        rotationAnimator.setInterpolator(new LinearInterpolator()); // 速度不变

        binding.imgListenStartandstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBound) {
                    if (musicService.isPlaying()) {
                        musicService.pauseMusic();
                        binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_play);
                        rotationAnimator.pause();
                    } else {
                        musicService.playMusic();
                        binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_stop);
                        if (rotationAnimator.isPaused()) {
                            rotationAnimator.resume();
                        } else {
                            rotationAnimator.start();
                        }
                    }
                }
            }
        });

        binding.sbListen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b && isBound) {
                    musicService.seekTo(i);
                    binding.tvListenNow.setText(formatTime(i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.icListenReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void updateProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (musicService == null) {
                    Log.d("listenTag", "空的musicService");
                }
                while (isBound && musicService != null && musicService.getCurrentPosition() <= musicService.getTotalDuration()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int curPostion = musicService.getCurrentPosition();
                            binding.sbListen.setProgress(curPostion);
                            binding.tvListenNow.setText(formatTime(curPostion));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        musicService.setOnMusicEndListenser(new MusicService.OnMusicEndListenser() {
            @Override
            public void onMusicEnd() {
                Log.d("listenTag", "回调了");
                musicService.rePlay();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("songIndex", curSongIndex);
        Log.d("listenTag", String.valueOf(curSongIndex));
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(conn);
            isBound = false;
        }
    }

    private String formatTime(int milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(milliseconds);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.silde_out_bottom);

    }

    private void dealWithSongName() {
        if (songName.contains("APT.")) {
            binding.imgListenSong.setImageResource(picList.get(0));
            binding.tvListenSongname.setText(nameList.get(0).toString());
            binding.tvListenSingnername.setText(singerList.get(0));
            curSongIndex = 1;
        } else if (songName.contains("Please Please Please")) {
            binding.imgListenSong.setImageResource(picList.get(1));
            binding.tvListenSongname.setText(nameList.get(1).toString());
            binding.tvListenSingnername.setText(singerList.get(1));
            curSongIndex = 2;
        } else if (songName.contains("不为谁而作的歌")) {
            binding.imgListenSong.setImageResource(picList.get(2));
            binding.tvListenSongname.setText(nameList.get(2).toString());
            binding.tvListenSingnername.setText(singerList.get(2));
            curSongIndex = 3;
        } else if (songName.contains("虚拟")) {
            binding.imgListenSong.setImageResource(picList.get(3));
            binding.tvListenSongname.setText(nameList.get(3).toString());
            binding.tvListenSingnername.setText(singerList.get(3));
            curSongIndex = 4;
        } else if (songName.contains("Ask me why")) {
            binding.imgListenSong.setImageResource(picList.get(4));
            binding.tvListenSongname.setText(nameList.get(4).toString());
            binding.tvListenSingnername.setText(singerList.get(4));
            curSongIndex = 5;
        } else if (songName.contains("Sacred Play Secret Place")) {
            binding.imgListenSong.setImageResource(picList.get(5));
            binding.tvListenSongname.setText(nameList.get(5).toString());
            binding.tvListenSingnername.setText(singerList.get(5));
            curSongIndex = 6;
        }
    }

    private List<String> nameList;
    private List<String> singerList;
    private List<Integer> picList;

    private void initList() {
        nameList = new ArrayList<>();
        singerList = new ArrayList<>();
        picList = new ArrayList<>();
        nameList.add("APT.");
        nameList.add("Please Please Please");
        nameList.add("不为谁而作的歌");
        singerList.add("ROSÉ/Bruno Mars");
        singerList.add("Sabrina Carpenter");
        singerList.add("林俊杰");
        picList.add(R.drawable.pic_song1);
        picList.add(R.drawable.pic_song3);
        picList.add(R.drawable.pic_song15);
        nameList.add("虚拟");
        nameList.add("Ask me why(眞人の決意)");
        nameList.add("Sacred Play Secret Place");
        singerList.add("陈粒");
        singerList.add("久石譲");
        singerList.add("Matryoshka");
        picList.add(R.drawable.pic_song2);
        picList.add(R.drawable.pic_song4);
        picList.add(R.drawable.pic_song13);
    }
}
