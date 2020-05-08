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

import android.graphics.Bitmap;
import android.util.Log;

import net.sf.json.JSONObject;

public class Share_item {
    private String name; //用户名字
    private String date; //时间 格式 2020/03/07
    private int praise_flag;  //点赞状态 1表示点赞 0表示未点击
    private int praise_num;  //点赞数量
    private String content; //文字内容
    private Bitmap head;
    private Bitmap bitmap1;  //图片1
    private Bitmap bitmap2;  //图片2
    private int date_num;

    public Share_item(String name){
        this.name = name;
        this.praise_flag = 0;
        this.praise_num = 0;
    }

    public void setJs(JSONObject js) {
        this.name = js.getString("name");
        this.date = js.getString("date");
        this.praise_flag = js.getInt("praise_flag");
        this.praise_num = js.getInt("praise_num");
        this.content = js.getString("content");
        this.date_num = js.getInt("date_num");;

    }
    public JSONObject getJS()
    {
        JSONObject js = new JSONObject();
        js.put("date_num",date_num);
        js.put("date",date);
        js.put("praise_flag",praise_flag);
        js.put("praise_num",praise_num);
        js.put("name",name);
        js.put("content",content);
        return js;

    }

    public int getDate_num() {
        return date_num;
    }

    public String getContent() {
        return content;
    }

    public Bitmap getBitmap1() {
        return bitmap1;
    }

    public Bitmap getBitmap2() {
        return bitmap2;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setPrise_flag() {
        this.praise_flag = this.praise_flag ^1;
    }

    public int getPraise_flag() {
        return praise_flag;
    }

    public void subPraise_num() {
        this.praise_num -= 1;
    }

    public void addPraise_num() {
        this.praise_num += 1;
    }

    public int getPraise_num() {
        return praise_num;
    }

    public Bitmap getHead() {
        return head;
    }

    public void setHead(Bitmap head) {
        this.head = head;
    }

    public void setBitmap1(Bitmap bitmap1) {
        this.bitmap1 = bitmap1;
    }

    public void setBitmap2(Bitmap bitmap2) {
        this.bitmap2 = bitmap2;
    }
}
