package com.example.tangcan0823.mintia_omiyagego;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by tangcan0823 on 2016/08/30.
 */
public class HomeVideo extends Activity {
    private String current;
    private String path ;
    private VideoView mVideoView;

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        path="android.resource://com.example.tangcan0823.mintia_omiyagego/"+R.raw.video;
        mVideoView = (VideoView) findViewById(R.id.videoView);
        if (path == null || path.length() == 0) {
            Toast.makeText(HomeVideo.this, "File URL/path is empty",
                    Toast.LENGTH_LONG).show();

        } else {
            // If the path has not changed, just start the media player
            if (path.equals(current) && mVideoView != null) {
                mVideoView.start();
                mVideoView.requestFocus();
                return;
            }
            current = path;
            mVideoView.setVideoPath(path);
            mVideoView.start();
            mVideoView.requestFocus();
        }
       mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               Intent in =new Intent(HomeVideo.this,MainActivity.class);
               startActivity(in);
               finish();
           }
       });
    }
    public void OnMySelfClick(View v)
    {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);//open the page
        startActivity(intent);

    }

}