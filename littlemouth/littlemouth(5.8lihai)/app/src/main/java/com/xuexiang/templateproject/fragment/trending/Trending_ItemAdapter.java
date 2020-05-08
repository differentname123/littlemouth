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

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.TCPSocket;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Trending_ItemAdapter extends RecyclerView.Adapter<Trending_ItemAdapter.MyHolder> {


    public Trending_ItemAdapter(){

    }

    private List<InfoData> list;

    public void setList(List<InfoData> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item3,parent,false);
        final MyHolder holder = new MyHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(parent.getContext(), Page3Activity.class);
                intent.putExtra("position", position);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
      //  Trending_item item= trending_items.get(position);
        InfoData infoData = list.get(position);
        holder.d_name.setText(infoData.dishName);
        holder.like_num.setText(infoData.likeNum+"");
        holder.u_name.setText(infoData.userName);
        holder.style.setText(infoData.style);


        Bitmap bmp_head = infoData.head;
        if (bmp_head != null)
            holder.head_img.setImageBitmap(bmp_head);
    }
    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView u_name;
        TextView d_name;
        ImageView head_img;
        TextView like_num;
        TextView style;
        public MyHolder(View itemView) {
            super(itemView);
            u_name=(TextView)itemView.findViewById(R.id.user_name);
            d_name =itemView.findViewById(R.id.dish_name);
            head_img=itemView.findViewById(R.id.primary_img);
            like_num = itemView.findViewById(R.id.like_num);
            style = itemView.findViewById(R.id.style);
        }
    }
}