package com.example.demo317;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Button button,send,Receive,disconnect;//定义按钮变量
    private int progress = 0;
    private Toast toast = null;
    private ImageView imageView = null;//定义图片变量
    private ExecutorService mThreadPool;//声明线程池
    private Socket socket;//声明socket变量
    private InputStreamReader isr;
    private String response;
    private InputStream is;
    private TCPSocket tcpsocket;
    private BufferedReader br;
    private OutputStream outputStream;
    public Bitmap bmp = null;
    private Handler handler=null;//用于线程改变控件
    private String uploadFile="/mnt/sdcard/qt.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);


       // 1、创建Handler对象（此处创建于主线程中便于更新UI）。


        //2、构建Runnable对象，在Runnable中更新界面。
        // 3、在子线程的run方法中向UI线程post，runnable对象来更新UI。
        handler=new Handler();

        // 初始化线程池
        mThreadPool = Executors.newCachedThreadPool();

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 连接服务器
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            // 创建Socket对象 & 指定服务端的IP 及 端口号
                            socket = new Socket("111.229.197.188", 12345); //服务器IP及端口
                            // 判断客户端和服务器是否连接成功
                            System.out.println(socket.isConnected());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
        });



        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//接收图片数据并且改变界面图片

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Log.d("TCP2", "开始初始化");
                            tcpsocket=new TCPSocket();
                            //接收图片数据
                            Log.d("TCP2", "初始化完成1");
                            while(tcpsocket==null);
                            Log.d("TCP2", "初始化完成");
                            bmp=tcpsocket.tcprecievephoto();
                            Log.d("TCP2", String.valueOf(bmp));
                            while(bmp==null);
                            Log.d("TCP4", String.valueOf(bmp));

                            //显示图片
                            handler.post(runnableUi);//使用主线程改变控件imageview

                            Log.d("TCP5", String.valueOf(bmp));
                            tcpsocket=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });
/*
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送数据
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            // 步骤1：从Socket 获得输出流对象OutputStream
                            // 该对象作用：发送数据
                            outputStream = socket.getOutputStream();

                            // 步骤2：写入需要发送的数据到输出流对象中
                            outputStream.write(("1+2"+"\n").getBytes());
                            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

                            // 步骤3：发送数据到服务端
                            outputStream.flush();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
        });*/

            //tcpsocket= new TCPSocket();

        disconnect= (Button) findViewById(R.id.disconnect);
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        //tcpsocket= new TCPSocket();


                   tcpsocket.tcpsendstring("1+2");
                   tcpsocket.tcpreceive();

            }
        });

        Receive = (Button) findViewById(R.id.Receive);
        Receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 接收json数据
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            tcpsocket=new TCPSocket();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tcpsocket.tcprecievejson();
                        }



                });


            }
        });

    }

    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            imageView.setImageBitmap(bmp);
            bmp=null;
        }

    };

}
