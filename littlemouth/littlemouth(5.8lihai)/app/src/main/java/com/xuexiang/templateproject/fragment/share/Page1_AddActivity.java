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

package com.xuexiang.templateproject.fragment.share;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.TCPSocket;

import net.sf.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Page1_AddActivity extends AppCompatActivity {
    private ImageView Im_add;
    private ImageView Im_add2;
    private int flag=0;
    private EditText Ed_contents;
    private int over_flag = 0;
    private Bitmap bmp_1 = null;
    private Bitmap bmp_2 = null;

    private int flag_num;
    private    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ActionBar act=getSupportActionBar();
        if(act!=null){
            act.hide();
        }



        ImageView Im_return = findViewById(R.id.page1_return);
        Im_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Im_add = findViewById(R.id.page1_add_photo);
        Im_add2 = findViewById(R.id.page1_add_photo2);
        Im_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
                flag=1;
            }
        });
        Im_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
                flag=2;
            }
        });
        handler=new Handler();
        Button send = findViewById(R.id.page1_send);
        Ed_contents = findViewById(R.id.page1_content);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Page1_AddActivity.this, "正在发表，请稍后",
                        Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
//年
                String year = String.valueOf( calendar.get(Calendar.YEAR) );
//月
                String month = String.valueOf( calendar.get(Calendar.MONTH)+1 );
//日
                String day = String.valueOf( calendar.get(Calendar.DAY_OF_MONTH) );

                String hour = String.valueOf( calendar.get(Calendar.HOUR_OF_DAY) );

                String mintue = String.valueOf( calendar.get(Calendar.MINUTE) );

                String second = String.valueOf( calendar.get(Calendar.SECOND) );

                String date = year+'/'+month+'/'+day+'/'+hour+':'+mintue+':'+second;


                Log.d("dzc",date);

                flag_num = (calendar.get(Calendar.MONTH)+1)*30*24*60*60+calendar.get(Calendar.DAY_OF_MONTH)*24*60*60+calendar.get(Calendar.HOUR_OF_DAY)*60*60+calendar.get(Calendar.MINUTE)*60+calendar.get(Calendar.SECOND);


                final JSONObject js = new JSONObject();

                js.put("date_num",flag_num);
                js.put("date",date);
                js.put("praise_flag",0);
                js.put("praise_num",0);
                js.put("name","dzc");
                js.put("content",Ed_contents.getText().toString());

                over_flag = 0;


                // 初始化线程池
                ExecutorService mThreadPool = Executors.newCachedThreadPool();

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {


                            Calendar calendar = Calendar.getInstance();

                            TCPSocket tcpsocket=new TCPSocket();
                            TCPSocket tcpsocket2=new TCPSocket();
                            TCPSocket tcpsocket1=new TCPSocket();

                            Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.page1_food1);

                            tcpsocket.page1_sendNewjson(js,flag_num);
                            tcpsocket.page1_sendphoto(bmp_1,"photo1",flag_num);
                            tcpsocket1.page1_sendphoto(bmp_2,"photo2",flag_num);
                            tcpsocket2.page1_sendphoto(bitmap,"head",flag_num);

                            bmp_1 = null;
                            bmp_2 = null;

                            handler.post(runnableUi);



                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });

                //while (over_flag == 0);

                finish();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                /* 将Bitmap设定到ImageView */
                if(flag == 1) {
                    bmp_1 = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    Im_add.setImageBitmap(bmp_1);
                }
                if(flag == 2) {
                    bmp_2 = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    Im_add2.setImageBitmap(bmp_2);
                }
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            Toast.makeText(Page1_AddActivity.this, "发表成功",
                    Toast.LENGTH_SHORT).show();
        }

    };

}
