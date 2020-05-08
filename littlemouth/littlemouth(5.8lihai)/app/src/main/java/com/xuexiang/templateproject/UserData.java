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

package com.xuexiang.templateproject;

import android.graphics.Bitmap;

import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UserData {
    public String account="游客666";//账号昵称
    public String password="123456";//密码
    public Bitmap bmp=null;//头像信息
    public String sign=null;
    String path="/data/data/com.xuexiang.templateproject/files";


    public UserData() throws IOException {
        FileCaozuo filecaozuo=new FileCaozuo(path);
        JSONObject js1=filecaozuo.read_txt("user.txt");
        if(js1!=null){
            this.account= js1.getString("account");
            this.password=js1.getString("password");
        }
        this.path=path;
        if(bmp!=null)
        this.bmp=filecaozuo.read_bitmap("bitmap.jpg");
    }
    public void add_txt(String filename, JSONObject object) throws IOException {
        FileCaozuo filecaozuo=new FileCaozuo(path);
        filecaozuo.WriteSysFile(filename,object);
    }
    public void add_bitmap(String filename,Bitmap bit) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 10, baos);
        byte[] data = baos.toByteArray();
        FileCaozuo filecaozuo=new FileCaozuo(path);
        filecaozuo.write_bitmap(filename,data);
    }



}
