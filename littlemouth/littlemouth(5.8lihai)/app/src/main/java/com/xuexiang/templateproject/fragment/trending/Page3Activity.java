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
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.TCPSocket;
import com.xuexiang.templateproject.core.BaseActivity;

import net.sf.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Page3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3_2);
        int position = getIntent().getIntExtra("position", 0);
        Log.e("position","position:"+position);
        getData(position);
    }

    private void getData(int position){
        Observable.create(new ObservableOnSubscribe<InfoData>() {
            @Override
            public void subscribe(ObservableEmitter<InfoData> e) throws Exception {
                TCPSocket tcpSocket = new TCPSocket();
                JSONObject jsonObject = tcpSocket.page3_get_txt(position);
                Bitmap head = tcpSocket.page3_get_photo("head", position);
                Bitmap photo1 = tcpSocket.page3_get_photo("photo1", position);
                Bitmap photo2 = tcpSocket.page3_get_photo("photo2", position);
                InfoData infoData = new InfoData();
                infoData.likeNum = jsonObject.getInt("like_num");
                infoData.content = jsonObject.getString("Content");
                infoData.userName = jsonObject.getString("user_name");
                infoData.dishName = jsonObject.getString("dish_name");
                infoData.style = jsonObject.getString("style");
                infoData.head = head;
                infoData.photo1 = photo1;
                infoData.photo2 = photo2;
                e.onNext(infoData);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InfoData>() {
                    @Override
                    public void accept(InfoData infoData) throws Exception {
                        ((ImageView)findViewById(R.id.image)).setImageBitmap(infoData.head);
                        ((ImageView)findViewById(R.id.share_img1)).setImageBitmap(infoData.photo1);
                        ((ImageView)findViewById(R.id.share_img2)).setImageBitmap(infoData.photo2);
                        ((TextView)findViewById(R.id.share_voice)).setText(infoData.content);
                        ((TextView)findViewById(R.id.title)).setText(infoData.userName);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("error", throwable.getLocalizedMessage());
                    }
                });
    }
}
