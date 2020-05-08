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
import android.graphics.BitmapFactory;
import android.util.Log;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCaozuo {
    private static String path;
    //private String path;
    public FileCaozuo(String path){
        this.path=path;
    }


    public static void write_bitmap(String fileName, byte[] content) throws IOException {
        try {
            File file = new File(path + "/"+fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            Log.d("file",file.getName());
            Log.d("file",file.getAbsolutePath());
            System.out.println("文件名：" + file.getName());
            System.out.println("路径：" + file.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(path +"/"+ fileName);
            fos.write(content);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Bitmap read_bitmap(String filename){
        Bitmap my_bmp = null;
        try {

            String str=path + "/"+filename;
            FileInputStream fis = new FileInputStream(str);
            if(fis==null)
                return null;
            int size = fis.available();
            System.out.println("size = " + size);
            byte[] data = new byte[size];
            fis.read(data);
            System.out.println("size = " + data.toString());
            ByteArrayOutputStream outPut = new ByteArrayOutputStream();
            my_bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            my_bmp.compress(Bitmap.CompressFormat.PNG, 100, outPut);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return my_bmp;
    }
    public void WriteSysFile(String filename, JSONObject object) throws IOException {
        File file = new File(path + "/"+filename);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            Log.d("file",file.getName());
            Log.d("file",file.getAbsolutePath());
            System.out.println("文件名：" + file.getName());
            System.out.println("路径：" + file.getAbsolutePath());
        }
         try {

                        FileWriter fw = new FileWriter(file, false);
                        fw.write(object.toString()+"\n");
                        fw.close();
                        Log.d("File","写入成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

    }

    public JSONObject read_txt(String filename) throws IOException {

        JSONObject js1 = null;
        FileReader file = new FileReader(path + "/"+filename);
        BufferedReader br = new BufferedReader(file);
        if(br==null)
            return null;
        String line;
        while((line = br.readLine()) != null)
        {
            js1 = JSONObject.fromObject(line);
            Log.d("file用户信息为", String.valueOf(js1));
            System.out.println(js1);
        }
        br.close();
        return js1;
    }

}
