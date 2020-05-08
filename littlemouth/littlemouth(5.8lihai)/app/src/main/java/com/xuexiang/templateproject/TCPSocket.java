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
import android.widget.ImageView;

import net.sf.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import org.json.JSONObject;

public class TCPSocket {
    private ExecutorService mThreadPool;
    public Socket socket;
    private InputStreamReader isr;
    private String response;
    private InputStream is;
    private BufferedReader br;
    public Bitmap bmp = null;
    private ImageView imageView = null;//定义图片变量
    private OutputStream outputStream;
    private Lock lock = new ReentrantLock();

    public TCPSocket() throws IOException {


        // 初始化线程池
        lock.lock();//上锁
        mThreadPool = Executors.newCachedThreadPool();


                try {

                    Log.d("TCP", "初始化前socket为"+ String.valueOf(socket));
                    // 创建Socket对象 & 指定服务端的IP 及 端口号
                    socket = new Socket("193.112.82.81", 12345); //云服务器IP及端口
                    //socket = new Socket("192.168.0.106", 12345); //本地服务器IP及端口
                    Log.d("TCP", "初始化后socket为"+ String.valueOf(socket));
                    // 判断客户端和服务器是否连接成功
                    System.out.println(socket.isConnected());

                } catch (IOException e) {
                    e.printStackTrace();
                }


        lock.unlock();
    }

    public void sendjson(String account, String password, String order) throws IOException {
        String st;
        DataOutputStream dos;
        JSONObject object = new JSONObject();
        object.put("order",order);
        object.put("account",account);
        object.put("password",password);
        st=object.toString();
        while(socket==null);
        Log.d("TCP", "发送请求前socket为"+ String.valueOf(socket));
        dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(st);
        dos.flush();
    }
    public String recivestr() throws IOException {
        BufferedReader in = null;//接收字符串
        String result;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//接收字符串
        result=in.readLine();
        return result;
    }

    public synchronized void tcpsendstring(final String mes) {//发送字符串
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("TCP","发送字符前为"+ String.valueOf(socket));
                    while (socket == null);//等待初始化完成
                    Log.d("TCP","发送字符时为"+ String.valueOf(socket));
                    // 步骤1：从Socket 获得输出流对象OutputStream
                    // 该对象作用：发送数据
                    outputStream = socket.getOutputStream();

                    // 步骤2：写入需要发送的数据到输出流对象中
                    outputStream.write((mes + "\n").getBytes());
                    // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

                    // 步骤3：发送数据到服务端
                    outputStream.flush();
                    Log.d("TCP","发送字符完成");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }


    public void tcpjsonrecieve() throws IOException {
        
        isr=new InputStreamReader(socket.getInputStream());
        br=new BufferedReader(isr);
        String str=br.readLine();

    }


    public synchronized Bitmap tcprecievephoto() {

        while(socket==null);
                    bmp=null;
                try {

                    Log.d("TCP", "进入图片接受");
                    tcpsendstring("4442");

                    DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                    int size = dataInput.readInt();
                    Log.d("TCP1", "接收图片大小为"+ String.valueOf(size));
                    byte[] data = new byte[size];
                    int len = 0;
                    while (len < size) {
                        len += dataInput.read(data, len, size - len);
                    }
                    ByteArrayOutputStream outPut = new ByteArrayOutputStream();
                    bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outPut);
                    dataInput.close();
                    outPut.close();

                    //imageView.setImageBitmap(bmp);
                    // handler.post(runnableUi);//使用主线程改变控件imageview
                    //Bitmap bitmap = BitmapFactory.decodeStream(dataInput);
                    //myHandler.obtainMessage().sendToTarget();

                } catch (IOException e) {
                    e.printStackTrace();
                }


        Log.d("TCP6", "图片数据为"+ String.valueOf(bmp));
        return bmp;

            }

    public synchronized void tcprecievejson() {
        bmp=null;
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
        try {

            Log.d("TCP1", "进入json接收");
            tcpsendstring("01");
            while(socket==null);
            Log.d("TCP1", "进入");
            DataInputStream in;
            String str;
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            str=in.readUTF();

            System.out.println("tcp输入信息为："+str);

            JSONObject js = JSONObject.fromObject(str);
            Log.d("TCP1jiesh", "进入json接收"+js.getString("string"));
/*
          DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            int size = dataInput.readInt();
            Log.d("TCP1", String.valueOf(size));
            byte[] data = new byte[size];
            int len = 0;
            while (len < size) {
                len += dataInput.read(data, len, size - len);
            }

            Log.d("TCP1接收到的数据", ""+data);
            ByteArrayOutputStream outPut = new ByteArrayOutputStream();
            System.out.println("类容 = " + data);
            JSONObject jsonobject =JSONObject.fromObject(data.toString());
            Log.d("TCP1jiesh", "进入json接收"+jsonobject.getString("string"));
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outPut);
            dataInput.close();
            outPut.close();*/

            //imageView.setImageBitmap(bmp);
            // handler.post(runnableUi);//使用主线程改变控件imageview
            //Bitmap bitmap = BitmapFactory.decodeStream(dataInput);
            //myHandler.obtainMessage().sendToTarget();

        } catch (IOException e) {
            e.printStackTrace();
        }
            }
        });
        Log.d("TCP6", String.valueOf(bmp));

    }



    public synchronized void tcpreceive() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {


                    try {
                        while(socket==null);
                        tcpsendstring("01");
                        // 步骤1：创建输入流对象InputStream
                        is = socket.getInputStream();

                        // 步骤2：创建输入流读取器对象 并传入输入流对象
                        // 该对象作用：获取服务器返回的数据
                        isr = new InputStreamReader(is);
                        br = new BufferedReader(isr);
                        Log.d("TCP准备读取", "response");
                        // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
                        response = br.readLine();

                        // 步骤4:通知主线程,将接收的消息显示到界面
                        Log.d("TCP数据", response);
                        is.close();
                        isr.close();
                        br.close();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }




            }
        });




    }

    //段正超专用
    /**************************************************************************************/
//type:   txt,head,photo1,photo2
    public void page1_sendjson(String type, int item, int flag_num) throws IOException {
        String st;
        DataOutputStream dos;
        JSONObject object = new JSONObject();
        object.put("order","page1");
        object.put("item", String.valueOf(item));
        object.put("type",type);

        String my_flag = String.valueOf(flag_num);

        object.put("date",my_flag);

        st=object.toString();
        Log.d("TCP", "发送请求前socket为"+ String.valueOf(socket));
        dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(st);
        dos.flush();
    }




    public synchronized Bitmap page1_rec_photo(String type, int item, int flag_num) {
        Bitmap my_bmp = null;

        while(socket==null);
        try {

            Log.d("TCP", "进入图片接受");
            page1_sendjson(type,item,flag_num);
            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            int size = dataInput.readInt();
            if(size!=0)
            {
                
            
                Log.d("photo_size", "接收图片大小为"+ String.valueOf(size));
                byte[] data = new byte[size];
                int len = 0;
                while (len < size) {
                    len += dataInput.read(data, len, size - len);
                }
                ByteArrayOutputStream outPut = new ByteArrayOutputStream();
                my_bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                my_bmp.compress(Bitmap.CompressFormat.PNG, 100, outPut);
                outPut.close();


            }
                //dataInput.close();

            

            //imageView.setImageBitmap(bmp);
            // handler.post(runnableUi);//使用主线程改变控件imageview
            //Bitmap bitmap = BitmapFactory.decodeStream(dataInput);
            //myHandler.obtainMessage().sendToTarget();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return my_bmp;

    }
    public synchronized JSONObject page1_rec_txt (int item, int flag_num) {
        JSONObject js = null;
        try {

            Log.d("TCP1", "进入json接收");
            page1_sendjson("txt",item,flag_num);
            while(socket==null);
            Log.d("TCP1", "进入");
            DataInputStream in;
            String str;
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            str=in.readUTF();

            System.out.println("tcp输入信息为："+str);

            js = JSONObject.fromObject(str);
            //Log.d("TCP1jiesh", "进入json接收"+js.getString("string"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return js;
    }
    public void page1_sendNewjson(JSONObject js, int flag_num) throws IOException {
        String st;
        DataOutputStream dos;
        page1_sendjson("txt",-1,flag_num);

        st=js.toString();
        dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(st);
        dos.flush();
    }

    public void page1_sendphoto(Bitmap bmap, String type, int flag_num) throws IOException {
        //发送图片
        page1_sendjson(type,-1,flag_num);
        DataOutputStream dos;
        int size = 0;
        try {
            if (bmap !=null) {
                dos = new DataOutputStream(socket.getOutputStream());


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                byte[] data = baos.toByteArray();

                size = data.length;




                Log.d("photo_size", "发送大小"+String.valueOf(size));


                dos.writeInt(size);
                dos.write(data);

                dos.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//彭钦\\
/*************************************************************************************/
//type:   txt:js,head,photo1,photo2:my_bmp
public void page3_sendjson(String type,int item) throws IOException {
    String st;
    DataOutputStream dos;
    JSONObject object = new JSONObject();
    object.put("order","page3");
    object.put("item",String.valueOf(item));
    object.put("type",type);
    st=object.toString();
    Log.d("TCP", "发送请求前socket为"+String.valueOf(socket));
    dos = new DataOutputStream(socket.getOutputStream());
    dos.writeUTF(st);
    dos.flush();
}
    public synchronized Bitmap page3_get_photo(String type,int item) {
        Bitmap my_bmp = null;

        while(socket==null);
        try {

            Log.d("TCP", "进入图片接受");
            page3_sendjson(type,item);
            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            int size = dataInput.readInt();
            Log.d("page3", "接收图片大小为"+String.valueOf(size));
            byte[] data = new byte[size];
            int len = 0;
            while (len < size) {
                len += dataInput.read(data, len, size - len);
            }
            ByteArrayOutputStream outPut = new ByteArrayOutputStream();
            my_bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            my_bmp.compress(Bitmap.CompressFormat.PNG, 100, outPut);
            outPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return my_bmp;











    }
    public synchronized JSONObject page3_get_txt (int item) {
        JSONObject js = null;
        try {

            Log.d("TCP1", "进入json接收");
            page3_sendjson("txt",item);
            while(socket==null);
            Log.d("TCP1", "进入");
            DataInputStream in;
            String str;
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            str=in.readUTF();

            System.out.println("tcp输入信息为："+str);

            js =JSONObject.fromObject(str);
            //Log.d("TCP1jiesh", "进入json接收"+js.getString("string"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return js;
    }

    public boolean isValid(){
        return socket.isConnected() || !socket.isClosed();
    }
    /*************************************************************************************/
//罗楚淋御用
//    ********************************************************************************

    public void page2_sendjson(String type, String date) throws IOException {
        String st;
        DataOutputStream dos;
        JSONObject object = new JSONObject();
        object.put("order","page2");
        object.put("type",type);
        object.put("date", date);
        st = object.toString();
        Log.d("TCP", "发送请求前socket为"+ String.valueOf(socket));
        dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(st);
        dos.flush();
    }


    public synchronized Bitmap page2_rec_photo(String type, String date){
        Bitmap photo = null;
        while (socket==null);
        try {
            Log.d("TCP", "进入图片接受");
            page2_sendjson(type,date);
            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            int size = dataInput.readInt();
            Log.d("TCP1", "接收图片大小为"+ String.valueOf(size));
            byte[] data = new byte[size];
            int len = 0;
            while (len < size) {
                len += dataInput.read(data, len, size - len);
            }
            ByteArrayOutputStream outPut = new ByteArrayOutputStream();
            photo = BitmapFactory.decodeByteArray(data, 0, data.length);
            photo.compress(Bitmap.CompressFormat.PNG, 100, outPut);
            dataInput.close();
            outPut.close();

            //imageView.setImageBitmap(bmp);
            // handler.post(runnableUi);//使用主线程改变控件imageview
            //Bitmap bitmap = BitmapFactory.decodeStream(dataInput);
            //myHandler.obtainMessage().sendToTarget();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return photo;

    }




    public synchronized JSONObject page2_rec_txt(String type ,String date) {
        JSONObject txt = null;
        try {
            Log.d("TCP1", "进入json接收");
            page2_sendjson(type,date);
            while(socket==null);
            Log.d("TCP1", "进入");
            DataInputStream in;
            String str;
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            str=in.readUTF();

            System.out.println("tcp输入信息为："+str);

            txt =JSONObject.fromObject(str);
            //Log.d("TCP1jiesh", "进入json接收"+js.getString("string"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return txt;
    }




    public void page2_sendNewjson(JSONObject js) throws IOException {
        String st;
        DataOutputStream dos;
        //page2_sendjson("txt",-1);

        st=js.toString();
        dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(st);
        dos.flush();
    }

}

