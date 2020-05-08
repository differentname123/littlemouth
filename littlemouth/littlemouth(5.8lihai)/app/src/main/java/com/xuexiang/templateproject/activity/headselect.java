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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.haha.perflib.Main;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.UserData;
import com.xuexiang.templateproject.fragment.share.Page1_AddActivity;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.io.FileNotFoundException;
import java.io.IOException;


public class headselect extends AppCompatActivity {
    private Button bt;
    private EditText Ed_contents;
    RadiusImageView rivHeadPic;
    String str=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headchoose);
        ActionBar act=getSupportActionBar();
        if(act!=null){
            act.hide();
        }
        Ed_contents=findViewById(R.id.jianyitxt);
        bt=findViewById(R.id.jianyi);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=Ed_contents.getText().toString();
                XToastUtils.toast("建议成功");
             finish();
            }
        });


    }



}
