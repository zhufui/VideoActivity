package com.example.admin.videoactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.admin.videoactivity.permissions.PermissionCallback;
import com.example.admin.videoactivity.permissions.PermissionGroup;
import com.example.admin.videoactivity.permissions.ZPermissions;

public class MainActivity extends AppCompatActivity implements PermissionCallback {
    Button bt;
    Button bt1;
    Button bt2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZPermissions.requestPermissions(MainActivity.this,
                PermissionReqCode.CODE0, MainActivity.this, PermissionGroup.build(
                        PermissionGroup.SD()
                ));
        context = this;
        bt = findViewById(R.id.bt);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.launch(context,
                        "/storage/emulated/0/DCIM/Camera/FULiveDemo_20181108_204928.mp4",
                        VideoActivity.TYPE_PATH);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.launch(context,
                        "android.resource://" + getPackageName() + "/" + R.raw.a1,
                        VideoActivity.TYPE_RAWPATH);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.launch(context,
                        "http://rarv.sucai.redocn.com/attachments/rars/201309/20130917/118MRedocn_2013091712033958_2269982.rar?st=Rjql7YiuoDoRosXxYhJDDg&e=1541713847",
                        VideoActivity.TYPE_URL);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ZPermissions.onRequestPermissionsResult(requestCode, grantResults, PermissionReqCode.CODE0);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void permissionGrant(int requestCode) {

    }

    @Override
    public void permissionDenied(int requestCode) {

    }
}
