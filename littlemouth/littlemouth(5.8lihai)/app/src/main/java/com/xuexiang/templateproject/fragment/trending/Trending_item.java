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

package com.xuexiang.templateproject.fragment.trending;

import android.graphics.Bitmap;

import net.sf.json.JSONObject;

public class Trending_item {
    private String dish_name;
    private String user_name;
    private String Content;
    private String style;
    private int like_num;
    private Bitmap head_img;
    private  Bitmap img1;
    private  Bitmap img2;

    public Trending_item(String name){
        this.user_name= name;

    }
    public String getUser_name(){return user_name;}

    public void setDish_name(String dish_name){this.dish_name=dish_name;}
    public String getDish_name() {
        return dish_name;
    }

    public void setContent(String content) {
        this.Content = content;
    }
    public String getContent() {
        return Content;
    }

    public void setStyle(String style){this.style=style;}
    public String getStyle(){return style;}

    public void setHead_img(Bitmap head_img){this.head_img=head_img;}
    public Bitmap getHead_img(){return head_img;}

    public void setLike_num(int like_num){this.like_num=like_num;}
    public int getLike_num(){return  like_num;}

    public void setJs(JSONObject js) {
        this.dish_name =js.getString("dish_name");
        this.user_name=js.getString("user_name");
        this.Content=js.getString("Content");
        this.like_num=js.getInt("like_num");
        this.style=js.getString("style");
    }
    public JSONObject getJS()
    {
        JSONObject js = new JSONObject();
        js.put("like_num",like_num);
        js.put("style",style);
        js.put("Content",Content);
        js.put("user_name",user_name);
        js.put("dish_name",dish_name);
        return js;

    }

}
