package com.example.tangcan0823.mintia_omiyagego;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class VideoControl extends AppCompatActivity {
    private Thread1 th1;//Declaration thread
    private Thread2 th2;
    private Handler handler1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_control);
        th1 = new Thread1();
        th1.setName("T1");//give it name
        th1.start();
        handler1 = th1.getHandler();
        while(handler1 == null){       //at first loop th1 do nothing and sleep
            SystemClock.sleep(100);
            handler1 = th1.getHandler();
        }
        handler1.post(new Runnable() {
            @Override
            public void run() {
            }
        });
        th2 = new Thread2();
        th2.setName("T2"); //give name
        th2.start();
    }
    public class Thread1 extends Thread{
        private Handler handler1;
        public Handler getHandler(){//注意哦，在run执行之前，返回的是null
            return handler1;
        }
        @Override
        public void run() {
            Looper.prepare();
            handler1 = new Handler(){
                public void handleMessage(android.os.Message msg) {    //if Thread1 get the message from Thread2
                    SystemClock.sleep(17000);
                    Intent intent = new Intent(getApplicationContext(),MainControl.class);//open the page
                    startActivity(intent);
                };
            };
            Looper.loop();

        }

    }
    public class Thread2 extends Thread{
        @Override
        public void run() {
            Message msg = Message.obtain();              //Thread2 send a message to Thread1
            msg.what = 1;
            msg.obj = System.currentTimeMillis()+"";
            handler1.sendMessage(msg);
            Intent intent = new Intent(getApplicationContext(),video.class);
            startActivity(intent);

        }
    }
}
