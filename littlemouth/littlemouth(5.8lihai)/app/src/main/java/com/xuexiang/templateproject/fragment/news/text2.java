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

import android.graphics.Bitmap;

import net.sf.json.JSONObject;

public class text2 {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String content;
    //private String date;
    private Bitmap head;
    private Bitmap photo;
    private static String date="0";

    public void setJs(JSONObject js){
        this.name = js.getString("name");
        this.date = js.getString("date");
        this.content = js.getString("content");
    }
    private String title;
    private   int pic;

    public text2(String title, int pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public  int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    //


    public static String getDate() {
        return date;
    }

    public String getContent(){
        return content;
    }

    public Bitmap getPhoto(){
        return photo;
    }
    public Bitmap getHeadPhoto(){
        return head;
    }
    public void setPhoto(Bitmap mp){
        this.photo = mp;
    }
    public void setHeadPhoto(Bitmap mp){
        this.head = mp;
    }

}

