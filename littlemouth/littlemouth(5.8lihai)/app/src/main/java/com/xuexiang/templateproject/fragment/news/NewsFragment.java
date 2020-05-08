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
import android.os.Handler;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.TCPSocket;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;

//import java.util.logging.Handler;

/**
 * @author xuexiang
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class NewsFragment extends BaseFragment {

    @BindView(R.id.textrecyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.textrefreshLayout)
    SmartRefreshLayout refreshLayout;

    private ExecutorService mThreadPool;

    private TextAdapter adapter;

    private JSONObject js;
    private Bitmap bmp;
    private Bitmap bmp1;
    private int times;
    private Handler handler;
    private List<text2> text2List;

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        times=0;
        List<text2> text2List = new ArrayList();
        this.text2List=text2List;
        /*text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o1));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o2));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o3));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o4));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o1));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o5));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o2));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o3));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o5));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o4));*/
        TextAdapter textAdapter = new TextAdapter(text2List,getContext());


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TextAdapter(text2List,getContext());
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initListeners() {
        //下拉刷新
        //List<text2> text2List = new ArrayList();
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求

            getData(0);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            //for(int i=0;i<10;i++)

            getData(text2List.size());
        });
        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
    private  void refresh(int index_num)
    {
        times++;
        refreshLayout.getLayout().postDelayed(() -> {
            //getData();
            //text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o4));
            Log.d("1",times+"");

            //TextAdapter.refresh(DemoDataProvider.getDemoNewInfos());
            //adapter.notifyItemChanged(index_num, R.id.textTitle);


            if(times==6){
                times=0;
                adapter.notifyDataSetChanged();
                if(index_num==0)
                {
                    recyclerView.scrollToPosition(6);
                    refreshLayout.finishRefresh();
                }
                else
                {
                    refreshLayout.finishLoadMore();
                }
            }
            else {
                //do nothing
            }

        }, 1000);
    }
    private void getData(int index_num){
        mThreadPool = Executors.newCachedThreadPool();
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    times=0;
                    for(int i=0;i<6;i++)
                    {
                        TCPSocket tcpSocket = new TCPSocket();
                        TCPSocket tcpSocket1 = new TCPSocket();
                        TCPSocket tcpSocket2 = new TCPSocket();
                        String date=text2.getDate();
                        js = tcpSocket.page2_rec_txt("txt", date);
                        Log.d("date", (text2.getDate()));
                        bmp = tcpSocket1.page2_rec_photo("pic", date);
                        bmp1 = tcpSocket2.page2_rec_photo("head", date);
                        Log.d("zxh", String.valueOf(js));
                        //Log.d("zxh", String.valueOf(bmp));
                        //Log.d("zxh", String.valueOf(bmp1));
                        if(js!=null) {
                            text2 tx = new text2(js.getString("content"), R.drawable.o4);
                            tx.setPhoto(bmp);
                            tx.setHeadPhoto(bmp1);
                            tx.setName(js.getString("name"));
                            tx.setJs(js);
                            text2List.add(index_num, tx);
                            refresh(index_num);
                        }
                        else
                        {
                            text2 tx = new text2("NULL", R.drawable.o4);
                            tx.setPhoto(bmp);
                            tx.setHeadPhoto(bmp1);
                            tx.setName("NULL");
                            JSONObject NULLjs=new JSONObject();
                            NULLjs.put("name","NULL");
                            NULLjs.put("date",i+1+"");
                            NULLjs.put("content","NULL");
                            tx.setJs(NULLjs);
                            //text2List.add(index_num, tx);
                            refresh(index_num);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /*int index=0;
    Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
            *//****//*


            //更新界面

            text2List.get(index).setJs(js);
            //text2List.get(index).setHead(bmp);
            //text2List.get(index).setBitmap1(bmp1);



            bmp=null;
            bmp1=null;
            *//*****//*


        }
    };*/
}
