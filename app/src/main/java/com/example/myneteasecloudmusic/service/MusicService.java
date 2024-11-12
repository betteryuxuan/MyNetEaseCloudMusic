package com.example.myneteasecloudmusic.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.ui.listen.ListenActivity;

public class MusicService extends Service {

    private final IBinder binder = new MyBinder();
    private MediaPlayer mediaPlayer;
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "MusicServiceChannel";

    public class MyBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("listenTag", "服务onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void startForegroundService() {
        Intent notificationIntent = new Intent(this, ListenActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("正在播放音乐")
                .setContentText("点击返回应用")
                .setSmallIcon(R.drawable.ic_add)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Music Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("listenTag", "服务onBind");
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.apt);
        }
        createNotificationChannel();
        startForegroundService();
        return binder;
    }

    @Override
    public void onDestroy() {
        Log.d("listenTag", "服务onDestroy");
        super.onDestroy();
//        mediaPlayer.stop();
//        mediaPlayer.release();
//        mediaPlayer = null;
//        stopSelf();
    }

    public void playOrPauseMusic() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }
    public void stopMusic() {
        if(mediaPlayer!=null){
            mediaPlayer.stop();

        }
    }

    public Boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }


    public int getProgress() {
        return mediaPlayer.getDuration();
    }


    public int getPlayPosition() {

        return mediaPlayer.getCurrentPosition();
    }

    public void seekToPositon(int msec) {
        mediaPlayer.seekTo(msec);
    }

}