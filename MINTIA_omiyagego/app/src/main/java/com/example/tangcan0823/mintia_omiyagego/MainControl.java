package com.example.tangcan0823.mintia_omiyagego;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainControl extends AppCompatActivity {
    private Thread1 th1;//Declaration thread
    private Thread2 th2;
    private Handler handler1;
    public static MainControl instance4= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        instance4 = this;


        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_control);
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
                    SystemClock.sleep(1000);

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);//open the page
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
            Intent intent = new Intent(getApplicationContext(),start.class);
            startActivity(intent);

        }
    }


}