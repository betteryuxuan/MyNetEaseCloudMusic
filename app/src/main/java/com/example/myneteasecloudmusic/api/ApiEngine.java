package com.example.myneteasecloudmusic.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiEngine {
    private static final String BASE_URL = "https://music.163.com/api/";

    public static String getRecommendSongs() {
        OkHttpClient client = new OkHttpClient();

        String url = BASE_URL + "/artist/songs?id=6452";
        Log.e("APIENGINETAG", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Log.e("APIENGINETAG", "Unexpected code " + response);
                return null; // 或者抛出一个异常
            }
            String responseData = response.body().string();
            Log.d("APIENGINETAG", responseData);
            return responseData; // 返回实际的响应数据
        } catch (IOException e) {
            Log.e("APIENGINETAG", "IOException occurred", e);
            return null; // 或者抛出一个异常
        }
    }
}
