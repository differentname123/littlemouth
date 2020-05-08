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
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class ShareFragment extends BaseFragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private Share_ItemAdapter adapt;

    private JSONObject js;
    private List<JSONObject> jss;
    private Handler handler;


    private Bitmap bmp;
    private Bitmap bmp1;
    private Bitmap bmp2;

    private List<Bitmap> bmps;
    private List<Share_item> share_items;

    private int over_flag;

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
        return R.layout.fragment_share;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        handler=new Handler();
        initList();



    }
    @Override
    protected void initListeners() {
        //下拉刷新
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {


                refreshData();


            }, 0);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {

                getData();


            }, 0);
        });
        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }

    private void initList(){

        share_items =new ArrayList<>();
        jss = new ArrayList<>();
        bmps = new ArrayList<>();


    }
    int refresh_num = 4;
    int index=0;
    private void refreshData(){
        // 初始化线程池
        ExecutorService mThreadPool = Executors.newCachedThreadPool();

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Calendar calendar = Calendar.getInstance();
                    int flag_num = (calendar.get(Calendar.MONTH)+1)*30*24*60*60+calendar.get(Calendar.DAY_OF_MONTH)*24*60*60+calendar.get(Calendar.HOUR_OF_DAY)*60*60+calendar.get(Calendar.MINUTE)*60+calendar.get(Calendar.SECOND);

                    TCPSocket tcpsocket=new TCPSocket();


                    jss.clear();
                    bmps.clear();
                    for(int i = 0;i<refresh_num;i++) {

                        if (i!=0)
                            flag_num = jss.get(i-1).getInt("date_num");

                        js = tcpsocket.page1_rec_txt(0, flag_num);
                        bmp = tcpsocket.page1_rec_photo("head", 0, flag_num);
                        bmp1 = tcpsocket.page1_rec_photo("photo1", 0, flag_num);
                        bmp2 = tcpsocket.page1_rec_photo("photo2", 0, flag_num);

                        jss.add(js);
                        bmps.add(bmp);
                        bmps.add(bmp1);
                        bmps.add(bmp2);
                        index=i;
                        Log.d("index?",String.valueOf(index));
                    }

                    //显示图片

                    handler.post(refreshUi);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
    int get_num = 3;
    int load_over= 0 ;

    private void getData(){
        // 初始化线程池
        ExecutorService mThreadPool = Executors.newCachedThreadPool();

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {

                    TCPSocket tcpsocket=new TCPSocket();

                    Log.d("index",String.valueOf(index));
                    int date_num;

                    for(int i = 0;i<get_num;i++) {

                        date_num = jss.get(index+i).getInt("date_num");

                        js = tcpsocket.page1_rec_txt(0, date_num);
                        if(js==null)
                        {
                            load_over=1;
                            break;
                        }
                        bmp = tcpsocket.page1_rec_photo("head", 0, date_num);
                        bmp1 = tcpsocket.page1_rec_photo("photo1", 0, date_num);
                        bmp2 = tcpsocket.page1_rec_photo("photo2", 0, date_num);

                        jss.add(js);
                        bmps.add(bmp);
                        bmps.add(bmp1);
                        bmps.add(bmp2);

                    }
                    //显示图片

                    handler.post(loadUi);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    Runnable   refreshUi=new  Runnable(){
        @Override
        public void run() {
            load_over=0;
            refreshLayout.finishRefresh();
            //更新界面
            Toast.makeText(getActivity(), "刷新成功",
                    Toast.LENGTH_SHORT).show();

            share_items.clear();
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());

            recyclerView.setLayoutManager(layoutManager);
            adapt=new Share_ItemAdapter(share_items);
            recyclerView.setAdapter(adapt);

            //更新界面

            for(int i=0;i<refresh_num;i++) {
                Share_item apple = new Share_item("Apple");
                share_items.add(apple);

                share_items.get(i).setJs(jss.get(i));
                share_items.get(i).setHead(bmps.get(i*3));
                share_items.get(i).setBitmap1(bmps.get(i*3+1));
                share_items.get(i).setBitmap2(bmps.get(i*3+2));
            }

            for(int i=0;i<refresh_num;i++) {
                adapt.notifyItemChanged(i, R.id.page1_name);
            }
            bmp=null;
            bmp1=null;
            bmp2=null;
        }

    };

    Runnable   loadUi=new  Runnable(){
        @Override
        public void run() {
            if(load_over!=1) {
                index += 1;
                //更新界面
                Toast.makeText(getActivity(), "刷新成功",
                        Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadMore();

                //更新界面
                for (int i = index; i < index + get_num; i++) {
                    Share_item apple = new Share_item("Apple");
                    share_items.add(apple);

                    share_items.get(i).setJs(jss.get(i));
                    share_items.get(i).setHead(bmps.get(i * 3));
                    share_items.get(i).setBitmap1(bmps.get(i * 3 + 1));
                    share_items.get(i).setBitmap2(bmps.get(i * 3 + 2));
                }


                for (int i = index; i < index + get_num; i++) {
                    adapt.notifyItemChanged(i, R.id.page1_name);

                }
                index += get_num - 1;
                bmp = null;
                bmp1 = null;
                bmp2 = null;
            }
            else
            {
                Toast.makeText(getActivity(), "已经到底啦",
                        Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadMore();
            }
        }

    };
}
