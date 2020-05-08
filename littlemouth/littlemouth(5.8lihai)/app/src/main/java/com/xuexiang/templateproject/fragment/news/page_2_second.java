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

package com.xuexiang.templateproject.fragment.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.templateproject.R;

public class page_2_second extends AppCompatActivity {
    private ImageView head;
    private ImageView content;
    private TextView contentText;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_2_second);
        head=findViewById(R.id.page1_head);
        content=findViewById(R.id.page2_content);
        contentText=findViewById(R.id.page1_content);
        name=findViewById(R.id.caiming);
        head.setImageBitmap(BmpCache.getHead());
        content.setImageBitmap(BmpCache.getContent());
        contentText.setText(BmpCache.getContenttext());
        name.setText(BmpCache.getName());
    }
    @Override
    protected void onDestroy(){
        BmpCache.clearBmpCache();
        super.onDestroy();
    }


}
