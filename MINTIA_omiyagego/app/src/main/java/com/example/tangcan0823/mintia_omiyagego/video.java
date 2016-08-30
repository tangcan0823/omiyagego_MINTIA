package com.example.tangcan0823.mintia_omiyagego;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class video extends AppCompatActivity {
    private VideoView videoView = null;
    private Point getSupportedPictureSizes;
    public static video instance= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        init();

        //videoView.setVideoPath(Environment.getExternalStorageDirectory() + File.separator + "0830-800-1080.mp4");
        videoView.requestFocus();
        videoView.start();


    }
    /*@Override
    public void onPause(){
        finish();
    }

    @Override
    public void onStop(){

    }*/
    private void init() {
        videoView = (VideoView) findViewById(R.id.videoView);
    }
    public void OnMySelfClick(View v)
    {

        Intent intent = new Intent(getApplicationContext(),MainControl.class);//open the page
        startActivity(intent);

    }


}
