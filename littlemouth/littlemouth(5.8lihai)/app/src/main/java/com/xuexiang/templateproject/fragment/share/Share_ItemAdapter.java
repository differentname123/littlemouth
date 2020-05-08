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
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.TCPSocket;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Share_ItemAdapter extends RecyclerView.Adapter<Share_ItemAdapter.MyHolder> {
    private List<Share_item> share_items;

    public Share_ItemAdapter(List<Share_item> my_item){
        this.share_items =my_item;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item1,parent,false);
        //将之前写好的list_view封装到一个View中
        final MyHolder holder=new MyHolder(view);
        holder.im_prise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Share_item item= share_items.get(position);
                item.setPrise_flag();
                if(item.getPraise_flag() == 0)
                    item.subPraise_num();
                else
                    item.addPraise_num();
                notifyItemChanged(position, R.id.page1_name);



//
                ExecutorService mThreadPool = Executors.newCachedThreadPool();

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {


                            TCPSocket tcpsocket=new TCPSocket();



                            tcpsocket.page1_sendNewjson(item.getJS(),item.getDate_num());


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });

                //holder.imagePrise.setImageDrawable(getResources());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {


        Share_item item= share_items.get(position);

        if(item.getPraise_flag() == 1)
            holder.im_prise.setImageResource(R.drawable.page1_praise_latter);
        else
            holder.im_prise.setImageResource(R.drawable.page1_praise);

        holder.tv_name.setText(item.getName());
        holder.tv_praisenum.setText(String.valueOf(item.getPraise_num()));
        holder.tv_date.setText(item.getDate());
        holder.tv_content.setText(item.getContent());

        holder.im_2.setVisibility(View.GONE);
        holder.im_1.setVisibility(View.GONE);

        Bitmap bmp_head = item.getHead();
        if (bmp_head != null)
            holder.im_head.setImageBitmap(bmp_head);

        Bitmap ph1 = item.getBitmap1();
        if (ph1 != null) {
            holder.im_1.setVisibility(View.VISIBLE);
            holder.im_2.setVisibility(View.INVISIBLE);
            holder.im_1.setImageBitmap(ph1);
        }


        Bitmap ph2 = item.getBitmap2();
        if (ph2 != null) {
            holder.im_2.setVisibility(View.VISIBLE);
            holder.im_2.setImageBitmap(ph2);

        }

    }



    @Override
    public int getItemCount() {
        return share_items.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_praisenum;
        TextView tv_date;
        TextView tv_content;
        ImageView im_head;
        ImageView im_1;
        ImageView im_2;


        ImageView im_prise;
        public MyHolder(View itemView) {
            super(itemView);
            tv_name =(TextView)itemView.findViewById(R.id.page1_name);
            im_prise =itemView.findViewById(R.id.page1_praise);
            tv_praisenum=itemView.findViewById(R.id.page1_prise_num);
            tv_date = itemView.findViewById(R.id.page1_date);
            tv_content = itemView.findViewById(R.id.page1_content);

            im_head = itemView.findViewById(R.id.page1_head);
            im_1 = itemView.findViewById(R.id.page1_photo1);
            im_2 = itemView.findViewById(R.id.page1_photo2);


        }
    }
}