package com.example.viewpage3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    TextView textView,textaccount,textpassword;
    String name1="abcdefgh";
    String account,password,order;
    String name2;
    int count = 0;
    private ExecutorService mThreadPool;//声明线程池
    private TCPSocket tcpsocket=null;//声明socket变量
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
        textaccount = findViewById(R.id.account);
        textpassword = findViewById(R.id.password);

        final String[] result = new String[1];
        Button signin=findViewById(R.id.Signin);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                order="denglu";
                account=textaccount.getText().toString();
                password=textpassword.getText().toString();

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                try {
                    tcpsocket = new TCPSocket();
                    tcpsocket.sendjson(account,password,order);
                    result[0]=tcpsocket.recivestr();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                    }
                });

                while(result[0]==null);
                result[0]=null;
                if(result[0].equals("true")){
                    Toast.makeText(LoginActivity.this, "登录成功"+account+result[0],
                            Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "未连接到服务器signin"+account+result[0],
                            Toast.LENGTH_SHORT).show();
                }





            }
        });
        Button signup=findViewById(R.id.Signup);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                order="zhuce";
                account=textaccount.getText().toString();
                password=textpassword.getText().toString();

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tcpsocket = new TCPSocket();
                            tcpsocket.sendjson(account,password,order);
                            result[0]=tcpsocket.recivestr();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                while(result[0]==null);
                result[0]=null;
                if(result[0].equals("true")){
                    Toast.makeText(LoginActivity.this, "注册成功"+account+result[0],
                            Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "未连接到服务器signin"+account+result[0],
                            Toast.LENGTH_SHORT).show();
                }


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
