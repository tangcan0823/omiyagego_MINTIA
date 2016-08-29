package com.example.tangcan0823.mintia_omiyagego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏*/
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        final ImageView imageview=(ImageView)findViewById(R.id.start);
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.actin);
        imageview.startAnimation(animation);
        animation.setFillAfter(true);
    }
}
