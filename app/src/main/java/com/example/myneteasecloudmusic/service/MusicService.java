package com.example.myneteasecloudmusic.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.myneteasecloudmusic.R;

public class MusicService extends Service {
    private OnMusicEndListenser onMusicEndListenser;
    private MediaPlayer mediaPlayer;
    private int[] musicFiles = {
            R.raw.music1,
            R.raw.music2,
            R.raw.music3,
            R.raw.music4,
            R.raw.music5,
            R.raw.music6
    };

    public interface OnMusicEndListenser {
        void onMusicEnd();
    }

    public void setOnMusicEndListenser(OnMusicEndListenser listenser) {
        this.onMusicEndListenser = listenser;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mediaPlayer = new MediaPlayer();
        int songIndex = intent.getIntExtra("songIndex",1);
        int musicResourceId = musicFiles[songIndex - 1];
        mediaPlayer = MediaPlayer.create(this, musicResourceId);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (onMusicEndListenser != null) {
                    onMusicEndListenser.onMusicEnd();
                }
            }
        });

        return new MusicBinder();
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    public void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
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

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
            mediaPlayer.seekTo(0); // 将播放位置重置到开头
            mediaPlayer.start();   // 开始播放
        }
    }


//    private void updateUI(int progress, int totalDuration) {
//        if (handler != null) {
//            Message message = Message.obtain(handler, UPDATE_UI, progress, totalDuration);
//            handler.sendMessage(message);
//        }
//    }
}