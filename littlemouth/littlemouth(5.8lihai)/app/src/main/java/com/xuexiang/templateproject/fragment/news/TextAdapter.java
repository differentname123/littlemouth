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

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.xuexiang.templateproject.R;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    private List<text2> textList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View textView;
        ImageView textImage;
        ImageView headImage;
        TextView textTitle;
        TextView name;

        public ViewHolder( View itemView) {
            super(itemView);
            textView = itemView;
            textImage = itemView.findViewById(R.id.textPic);
            textTitle = itemView.findViewById(R.id.textTitle);
            headImage = itemView.findViewById(R.id.Head);
            name = itemView.findViewById(R.id.Name);
        }
    }

    public TextAdapter(List<text2> textList, Context context) {
        this.textList = textList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item2,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                text2 text= textList.get(position);
                if(text.getTitle().length()>30)
                {
                    String hint=text.getTitle().substring(0,29)+"......(详情请点击图片查看)";
                    Toast.makeText(v.getContext(),hint,
                            Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(v.getContext(),text.getTitle(),
                            Toast.LENGTH_SHORT).show();


                //notifyItemChanged(position,R.id.textTitle);
            }
        });
        holder.textImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               /*ExecutorService mThreadPool = Executors.newCachedThreadPool();
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String date = "0";
                            TCPSocket tcpsocket = new TCPSocket();
                            tcpsocket.page2_sendjson("pic",date);
                            tcpsocket.page2_sendjson("head",date);
                            tcpsocket.page2_sendjson("txt",date);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });//TCP*/

                Intent intent = new Intent(context, page_2_second.class);
                BmpCache BmpTransfer=new BmpCache();
                BmpTransfer.setHead(textList.get(holder.getAdapterPosition()).getHeadPhoto());
                BmpTransfer.setContent(textList.get(holder.getAdapterPosition()).getPhoto());
                BmpTransfer.setContenttext(textList.get(holder.getAdapterPosition()).getTitle());
                BmpTransfer.setName(textList.get(holder.getAdapterPosition()).getName());
                context.startActivity(intent);
                /*int position = holder.getAdapterPosition();
                text2 text= textList.get(position);
                Toast.makeText(v.getContext(),text.getTitle(),
                        Toast.LENGTH_SHORT).show();*/
            }
        });
        return holder;
        /*ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;*/
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {

        text2 text = textList.get(i);
        Bitmap bmp_content = text.getPhoto();
        Bitmap bmp_head = text.getHeadPhoto();
        viewHolder.textImage.setImageBitmap(bmp_content);
        viewHolder.headImage.setImageBitmap(bmp_head);
        viewHolder.name.setText(textList.get(i).getName());
        /****/
        ViewGroup.LayoutParams params = viewHolder.textImage.getLayoutParams();
        params.height = params.height ;
        viewHolder.textImage.setLayoutParams(params);
        viewHolder.textTitle.setText(text.getTitle());
    }

    private StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
            (2, StaggeredGridLayoutManager.VERTICAL);

    private AnimatedStateListDrawable layoutInflater;

    @Override
    public int getItemCount() {
        return textList.size();
    }
}
