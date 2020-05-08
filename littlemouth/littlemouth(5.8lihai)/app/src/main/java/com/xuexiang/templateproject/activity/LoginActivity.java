/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.templateproject.activity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.TextView;

import com.xuexiang.templateproject.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.xuexiang.templateproject.FileCaozuo;
import com.xuexiang.templateproject.OnSwipeTouchListener;
import com.xuexiang.templateproject.TCPSocket;
import com.xuexiang.templateproject.UserData;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;
    Button signin;
    Button signup;
    TextView textView,textaccount,textpassword;
    String name1="abcdefgh";
    String account,password,order;
    String name2;
    int count = 0;
    final String[] result = new String[1];
    private ExecutorService mThreadPool;//声明线程池
    private TCPSocket tcpsocket=null;//声明socket变量
    public void Findthing(){
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textaccount = findViewById(R.id.account);
        textpassword = findViewById(R.id.password);
        signin=findViewById(R.id.Signin);
        signup=findViewById(R.id.Signup);
    }
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
        Findthing();//找要用到的控件
        Log.d("file","存储账号信息");
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

                if(result[0].equals("true")){

                    JSONObject object = new JSONObject();
                    object.put("order",order);
                    object.put("account",account);
                    object.put("password",password);


                    try {
                        final String path = getFilesDir().getPath();
                        UserData user= new UserData();
                        user.add_txt("account.txt",object);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, "登录成功",
                            Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(LoginActivity.this, "账号或密码错误",
                            Toast.LENGTH_SHORT).show();
                }
                result[0]=null;



                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

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
                if(result[0].equals("true")){
                    Toast.makeText(LoginActivity.this, "注册成功",
                            Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(LoginActivity.this, "该账号已存在请直接登录",
                            Toast.LENGTH_SHORT).show();
                }
                result[0]=null;

                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
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
