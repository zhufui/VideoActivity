package com.example.admin.videoactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.videoactivity.manager.VideoManager;
import com.example.admin.videoactivity.weight.FullScreenVideoView;

public class VideoActivity extends AppCompatActivity {
    public static final String EXTRA_PATH = "path";
    public static final String EXTRA_TYPE = "type";

    public static final int TYPE_PATH = 1;
    public static final int TYPE_URL = 2;
    public static final int TYPE_RAWPATH = 3;
    private int type;

    FullScreenVideoView fullScreenVideoView;
    VideoManager videoManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_video);
        fullScreenVideoView = findViewById(R.id.fsv);
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        videoManager = new VideoManager(this, fullScreenVideoView);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt(EXTRA_TYPE);
        String path = bundle.getString(EXTRA_PATH);
        switch (type) {
            case TYPE_PATH:
                videoManager.setPath(path);
                break;
            case TYPE_URL:
                videoManager.setUrl(path);
                break;
            case TYPE_RAWPATH:
                videoManager.setRawPath(path);
                break;
        }
        videoManager.init();
        videoManager.start();
    }

    public static void launch(Context context, String path, int type) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_PATH, path);
        bundle.putInt(EXTRA_TYPE, type);
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
