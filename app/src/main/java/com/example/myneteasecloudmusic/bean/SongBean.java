package com.example.myneteasecloudmusic.bean;

public class SongBean {
    private String songName;
    private String singnerName;
    private Integer picture;


    public SongBean() {
    }

    public SongBean(String songName, String singnerName, Integer picture) {
        this.songName = songName;
        this.singnerName = singnerName;
        this.picture = picture;
    }

    /**
     * 获取
     * @return songName
     */
    public String getSongName() {
        return songName;
    }

    /**
     * 设置
     * @param songName
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * 获取
     * @return singnerName
     */
    public String getSingnerName() {
        return singnerName;
    }

    /**
     * 设置
     * @param singnerName
     */
    public void setSingnerName(String singnerName) {
        this.singnerName = singnerName;
    }

    /**
     * 获取
     * @return picture
     */
    public Integer getPicture() {
        return picture;
    }

    /**
     * 设置
     * @param picture
     */
    public void setPicture(Integer picture) {
        this.picture = picture;
    }

    public String toString() {
        return "SongBean{songName = " + songName + ", singnerName = " + singnerName + ", picture = " + picture + "}";
    }
}
