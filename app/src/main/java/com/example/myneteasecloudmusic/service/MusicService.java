package com.example.myneteasecloudmusic.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myneteasecloudmusic.R;

public class MusicService extends Service {
    private static final String CHANNEL_ID = "MusicServiceChannel";
    private OnMusicEndListenser onMusicEndListenser;
    private MediaPlayer mediaPlayer;
    private int currentSongIndex = -1;
    private int[] musicFiles = {R.raw.music1, R.raw.music2, R.raw.music3, R.raw.music4, R.raw.music5, R.raw.music6, R.raw.music7};

    public interface OnMusicEndListenser {
        void onMusicEnd();
    }

    public void setOnMusicEndListenser(OnMusicEndListenser listenser) {
        this.onMusicEndListenser = listenser;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("listenTag", "MusicService: onCreate: ");
        if (mediaPlayer == null)
            mediaPlayer = new MediaPlayer();
        Log.d("listenTag", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        int songIndex = intent.getIntExtra("songIndex", 1) - 1;
//        Log.d("listenTag", "onBind: " + songIndex);
//
//        if (currentSongIndex != songIndex) {
//            if (!mediaPlayer.isPlaying()) {
//                mediaPlayer = MediaPlayer.create(this, musicFiles[songIndex]);
//                currentSongIndex = songIndex;
//
//                mediaPlayer.setOnCompletionListener(mediaPlayer -> {
//                    if (onMusicEndListenser != null) {
//                        onMusicEndListenser.onMusicEnd();
//                    }
//                });
//                playMusic();
//            } else {
//                mediaPlayer.stop();
//                mediaPlayer.reset();
//                mediaPlayer = MediaPlayer.create(this, musicFiles[songIndex]);
//                currentSongIndex = songIndex;
//
//                mediaPlayer.setOnCompletionListener(mediaPlayer -> {
//                    if (onMusicEndListenser != null) {
//                        onMusicEndListenser.onMusicEnd();
//                    }
//                });
//            }
//        }

        return new MusicBinder();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int songIndex = intent.getIntExtra("songIndex", 1);
        Log.d("listenTag", "onStartCommand: " + songIndex);

        if (currentSongIndex != songIndex) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this, musicFiles[songIndex]);
            currentSongIndex = songIndex;

            mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                if (onMusicEndListenser != null) {
                    onMusicEndListenser.onMusicEnd();
                }
            });
        }


        createNotificationChannel();
        Notification notification = createMusicNotification(this);

        startForeground(1, notification);

        return START_STICKY;
    }

    public Notification createMusicNotification(Context context) {
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("正在播放音乐")
                .setContentText(getSongName())
                .setSmallIcon(R.drawable.ic_item_1_selected)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .build();

        return notification;
    }

    private String getSongName() {
        switch (currentSongIndex) {
            case 0:
                return "APT.";
            case 1:
                return "Please Please Please";
            case 2:
                return "不为谁而作的歌";
            case 3:
                return "虚拟";
            case 4:
                return "Ask me why(眞人の決意)";
            case 5:
                return "Sacred Play Secret Place";
            case 6:
                return "Espresso";
            default:
                return "NULL";

        }
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }


    public void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            sendBroadcastManager();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void continueMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void playSongByIndex(int songIndex) {
        if (currentSongIndex != songIndex) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this, musicFiles[songIndex]);
            currentSongIndex = songIndex;

            mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                if (onMusicEndListenser != null) {
                    onMusicEndListenser.onMusicEnd();
                }
            });
        }
        updateNotification();
        playMusic();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }


    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        stopForeground(true);
        super.onDestroy();
    }

    public int getTotalDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int position) {
        mediaPlayer.seekTo(position);
    }

    public void rePlay() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    private void sendBroadcastManager() {
        Intent intent = new Intent("com.example.myneteasecloudmusic.MUSIC_NAME");
        intent.putExtra("songName", getSongName());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    // 通知通道，用于前台服务的通知
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "音乐播放服务通道", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    private void updateNotification() {
        Notification notification = createMusicNotification(this);
        startForeground(1, notification);
    }

}
