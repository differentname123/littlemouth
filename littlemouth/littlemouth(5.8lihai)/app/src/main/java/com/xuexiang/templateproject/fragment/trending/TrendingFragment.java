/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.TCPSocket;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.fragment.share.Share_ItemAdapter;
import com.xuexiang.templateproject.fragment.share.Share_item;
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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xuexiang
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class TrendingFragment extends BaseFragment {
    @BindView(R.id.recyclerview3)
    RecyclerView recyclerView3;
    @BindView(R.id.refreshLayout3)
    SmartRefreshLayout refreshLayout3;

    private TCPSocket mTcpSocket;

    private List<InfoData> list;

    private Trending_ItemAdapter trending_itemAdapter;

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
        return R.layout.fragment_trending;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        list = new ArrayList<>();
        trending_itemAdapter = new Trending_ItemAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView3.setLayoutManager(linearLayoutManager);
        recyclerView3.setAdapter(trending_itemAdapter);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        refreshLayout3.setOnRefreshListener(refreshLayout -> {
            getData(0);
        });
        //上拉加载
        refreshLayout3.setOnLoadMoreListener(refreshLayout -> {
            getData(list.size());
        });
        refreshLayout3.autoRefresh();//第一次进入触发自动刷新，演示效果
    }

    private void getData(int index){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                for(int i = 0; i <= index+5; i++){
                    mTcpSocket = new TCPSocket();
                    JSONObject jsonObject = mTcpSocket.page3_get_txt(i);
                    if(jsonObject == null)
                        break;
                    Bitmap pic = mTcpSocket.page3_get_photo("head", i);
                    Bitmap photo1 = mTcpSocket.page3_get_photo("photo1", i);
                    Bitmap photo2 = mTcpSocket.page3_get_photo("photo2", i);
                    InfoData infoData = new InfoData();
                    infoData.likeNum = jsonObject.getInt("like_num");
                    infoData.content = jsonObject.getString("Content");
                    infoData.userName = jsonObject.getString("user_name");
                    infoData.dishName = jsonObject.getString("dish_name");
                    infoData.style = jsonObject.getString("style");
                    infoData.head = pic;
                    infoData.photo1 = photo1;
                    infoData.photo2 = photo2;
                    list.add(infoData);
                }
                e.onNext(new InfoData());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object infoData) throws Exception {
                        trending_itemAdapter.setList(list);
                        refreshLayout3.finishRefresh();
                        refreshLayout3.finishLoadMore();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("error", throwable.getLocalizedMessage());
                    }
                });
    }
}
