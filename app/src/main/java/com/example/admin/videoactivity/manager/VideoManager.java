package com.example.admin.videoactivity.manager;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoManager {
    public static final String TAG = VideoManager.class.getSimpleName();
    private Context context;
    private VideoView videoView;
    private String path;
    private String rawPath;
    private String url;

    private boolean defaultMediaController = false;//设置默认的控制器

    private VideoListener videoListener;

    public VideoManager(Context context, VideoView videoView) {
        this.context = context;
        this.videoView = videoView;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 设置文件路径
     * 例如：/storage/emulated/0/DCIM/Camera/FULiveDemo_20181108_171429.mp4
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 设置raw目录下的文件路径
     * 例如：android.resource://包名/R.raw.my_video_file
     *
     * @param rawPath
     */
    public void setRawPath(String rawPath) {
        this.rawPath = rawPath;
    }

    public void setVideoListener(VideoListener videoListener) {
        this.videoListener = videoListener;
    }

    /**
     * 初始化
     */
    public void init() {
        if (defaultMediaController) {
            videoView.setMediaController(new MediaController(context));
        }

        if (!TextUtils.isEmpty(path)) {
            videoView.setVideoPath(path);
        }

        if (!TextUtils.isEmpty(rawPath)) {
            videoView.setVideoURI(Uri.parse(rawPath));
        }

        if (!TextUtils.isEmpty(url)) {
            videoView.setVideoURI(Uri.parse(url));
        }

        //播放准备
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "播放准备完成");
                if (videoListener != null) {
                    videoListener.onPrepared();
                }
            }
        });

        //播放完毕的回调
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "播放完成");
                if (videoListener != null) {
                    videoListener.onCompletion();
                }
            }
        });

        // 发生错误的回调
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "播放错误");
                return false;
            }
        });

    }

    /**
     * 开始播放
     */
    public void start() {
        if (videoView == null) {
            return;
        }
        videoView.start();
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (videoView != null && videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
        }
    }
}
