package com.example.viewpage3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    String name1="abcdefgh";
    String name2;
    int count = 0;
    private ExecutorService mThreadPool;//声明线程池
    private TCPSocket tcpsocket;//声明socket变量
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        mThreadPool = Executors.newCachedThreadPool();
        ActionBar act=getSupportActionBar();
        if(act!=null){
            act.hide();
        }


        Random ra =new Random();
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        Button signin=findViewById(R.id.Signin);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                try {
                    tcpsocket = new TCPSocket();
                    tcpsocket.tcprecievejson();
                    tcpsocket.tcpsendstring("zhuxiaohu");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    }
                });
                Toast.makeText(LoginActivity.this, "未连接到服务器signin",
                        Toast.LENGTH_SHORT).show();

            }
        });
        Button signup=findViewById(R.id.Signup);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(LoginActivity.this, "未连接到服务器",
                        Toast.LENGTH_SHORT).show();

            }
        });

        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0) {

                    textView.setText("小可爱");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.a);
                    textView.setText("小傻瓜");
                    count = 0;
                }
            }

            public void onSwipeLeft() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.b);
                    textView.setText("小可爱");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.a);
                    textView.setText("小傻瓜");
                    count = 0;
                }
            }

            public void onSwipeBottom() {
            }

        });
    }
}
