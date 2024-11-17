package com.example.myneteasecloudmusic.ui.listen.view;

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
import com.example.myneteasecloudmusic.bean.SongBean;
import com.example.myneteasecloudmusic.databinding.ActivityListenBinding;
import com.example.myneteasecloudmusic.service.MusicService;
import com.example.myneteasecloudmusic.ui.listen.fragment.SongListBottomSheetFragment;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

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
    private List<SongBean> songList;

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


            // 绑定服务后，同步播放进度和长度，播放图标
            updateProgress();
            musicService.playMusic();


            musicService.setOnMusicEndListenser(new MusicService.OnMusicEndListenser() {
                @Override
                public void onMusicEnd() {
                    Log.d("listenTag", "回调了");
                    musicService.rePlay();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    private void updateicon() {
        if (isBound) {
            if (musicService.isPlaying()) {
                binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_stop);
                if (rotationAnimator.isPaused()) {
                    rotationAnimator.resume();
                } else if (!rotationAnimator.isStarted()) {
                    rotationAnimator.start();
                }
            } else {
                binding.imgListenStartandstop.setImageResource(R.drawable.ic_listen_play);
                rotationAnimator.pause();
            }
        }
    }


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
        Log.d("listenTag", "ListenActivity:" + songName);
        initList();
        dealWithSongName();
        initRotationAnimation();

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

        binding.imgListenReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.imgListenStartandstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView2(binding.imgListenStartandstop);
                isAnimatiorPlay();
            }
        });

        binding.imgListenNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView2(binding.imgListenNext);

                int nextIndex = (curSongIndex + 1) % songList.size();
                musicService.playSongByIndex(nextIndex);
                curSongIndex = nextIndex;

                // activity UI的更新
                SongBean nextSong = songList.get(nextIndex);
                binding.tvListenSingnername.setText(nextSong.getSingnerName());
                binding.tvListenSongname.setText(nextSong.getSongName());
                binding.imgListenSong.setImageResource(nextSong.getPicture());
                binding.tvListenDuration.setText(formatTime(musicService.getTotalDuration()));
                binding.sbListen.setMax(musicService.getTotalDuration());
                rotationAnimator.start();
                updateProgress();
            }
        });
        binding.imgListenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.setAnimateView2(binding.imgListenBack);

                int nextIndex = (curSongIndex - 1 + songList.size()) % songList.size();
                musicService.playSongByIndex(nextIndex);
                curSongIndex = nextIndex;

                // activity UI的更新
                SongBean nextSong = songList.get(nextIndex);
                binding.tvListenSingnername.setText(nextSong.getSingnerName());
                binding.tvListenSongname.setText(nextSong.getSongName());
                binding.imgListenSong.setImageResource(nextSong.getPicture());
                binding.tvListenDuration.setText(formatTime(musicService.getTotalDuration()));
                binding.sbListen.setMax(musicService.getTotalDuration());
                rotationAnimator.start();
                updateProgress();

            }
        });
        binding.imgListenSonglist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongListBottomSheetFragment fragment = new SongListBottomSheetFragment(songList, curSongIndex);
                fragment.show(getSupportFragmentManager(), "SongListBottomSheetFragment");
            }
        });

    }

    public void playBySongName(String songNaem) {
        this.songName = songNaem;
        Log.d("songListenTag", "2  " + songName);
        dealWithSongName();
        Log.d("songListenTag", "3  " + curSongIndex);
        AnimationUtil.setAnimateView2(binding.imgListenNext);

        musicService.playSongByIndex(curSongIndex);

        SongBean nextSong = songList.get(curSongIndex);
        binding.tvListenSingnername.setText(nextSong.getSingnerName());
        binding.tvListenSongname.setText(nextSong.getSongName());
        binding.imgListenSong.setImageResource(nextSong.getPicture());
        binding.tvListenDuration.setText(formatTime(musicService.getTotalDuration()));
        binding.sbListen.setMax(musicService.getTotalDuration());
        rotationAnimator.start();
        updateProgress();
    }

    private void initRotationAnimation() {
        rotationAnimator = ObjectAnimator.ofFloat(binding.imgListenSong, "rotation", 0f, 360f);
        rotationAnimator.setDuration(50000); // 设置动画持续时间，单位毫秒
        rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 无限循环
        rotationAnimator.setInterpolator(new LinearInterpolator()); // 速度不变
    }

    private void isAnimatiorPlay() {
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
                            updateicon();
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

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("songIndex", curSongIndex);
        Log.d("listenTag", String.valueOf(curSongIndex));
        startService(intent);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("listenTag", "ListenActivity: onDestroy");
        rotationAnimator.cancel();
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
        if (songName.equals(songList.get(0).getSongName())) {
            binding.imgListenSong.setImageResource(songList.get(0).getPicture());
            binding.tvListenSongname.setText(songList.get(0).getSongName());
            binding.tvListenSingnername.setText(songList.get(0).getSingnerName());
            curSongIndex = 0;
        } else if (songName.equals(songList.get(1).getSongName())) {
            binding.imgListenSong.setImageResource(songList.get(1).getPicture());
            binding.tvListenSongname.setText(songList.get(1).getSongName());
            binding.tvListenSingnername.setText(songList.get(1).getSingnerName());
            curSongIndex = 1;
        } else if (songName.equals(songList.get(2).getSongName())) {
            binding.imgListenSong.setImageResource(songList.get(2).getPicture());
            binding.tvListenSongname.setText(songList.get(2).getSongName());
            binding.tvListenSingnername.setText(songList.get(2).getSingnerName());
            curSongIndex = 2;
        } else if (songName.equals(songList.get(3).getSongName())) {
            binding.imgListenSong.setImageResource(songList.get(3).getPicture());
            binding.tvListenSongname.setText(songList.get(3).getSongName());
            binding.tvListenSingnername.setText(songList.get(3).getSingnerName());
            curSongIndex = 3;
        } else if (songName.equals(songList.get(4).getSongName())) {
            binding.imgListenSong.setImageResource(songList.get(4).getPicture());
            binding.tvListenSongname.setText(songList.get(4).getSongName());
            binding.tvListenSingnername.setText(songList.get(4).getSingnerName());
            curSongIndex = 4;
        } else if (songName.equals(songList.get(5).getSongName())) {
            binding.imgListenSong.setImageResource(songList.get(5).getPicture());
            binding.tvListenSongname.setText(songList.get(5).getSongName());
            binding.tvListenSingnername.setText(songList.get(5).getSingnerName());
            curSongIndex = 5;
        } else if (songName.equals(songList.get(6).getSongName())) {
            binding.imgListenSong.setImageResource(songList.get(6).getPicture());
            binding.tvListenSongname.setText(songList.get(6).getSongName());
            binding.tvListenSingnername.setText(songList.get(6).getSingnerName());
            curSongIndex = 6;
        }
    }


    public void initList() {
        songList = new ArrayList<>();
        songList.add(new SongBean("APT.", "ROSÉ/Bruno Mars", R.drawable.pic_song1));
        songList.add(new SongBean("Please Please Please", "Sabrina Carpenter", R.drawable.pic_song3));
        songList.add(new SongBean("不为谁而作的歌", "林俊杰", R.drawable.pic_song15));
        songList.add(new SongBean("虚拟", "陈粒", R.drawable.pic_song2));
        songList.add(new SongBean("Ask me why(眞人の決意)", "久石譲", R.drawable.pic_song4));
        songList.add(new SongBean("Sacred Play Secret Place", "Matryoshka", R.drawable.pic_song13));
        songList.add(new SongBean("Espresso", "Sabrina Carpenter", R.drawable.pic_singner_8));
    }
}
