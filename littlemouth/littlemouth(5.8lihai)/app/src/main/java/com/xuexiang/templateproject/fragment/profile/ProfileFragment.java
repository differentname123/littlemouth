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

package com.xuexiang.templateproject.fragment.profile;

import android.content.Intent;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.TCPSocket;
import com.xuexiang.templateproject.UserData;
import com.xuexiang.templateproject.activity.LoginActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.fragment.AboutFragment;
import com.xuexiang.templateproject.activity.headselect;
import com.xuexiang.templateproject.fragment.SettingsFragment;
import com.xuexiang.templateproject.fragment.share.Page1_AddActivity;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class ProfileFragment extends BaseFragment implements SuperTextView.OnSuperTextViewClickListener {
    @BindView(R.id.riv_head_pic)
    RadiusImageView rivHeadPic;
    @BindView(R.id.menu_settings)
    SuperTextView menuSettings;
    @BindView(R.id.menu_about)
    SuperTextView menuAbout;
    @BindView(R.id.zhanghao)
    SuperTextView zhanghao;
    @BindView(R.id.fankui)
    SuperTextView fankui;
    @BindView(R.id.tongzhi)
    SuperTextView tongzhi;
    @BindView(R.id.riv_head)
    SuperTextView rivHead;
    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }
    int flag=0;
    String st = null;
    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {



    }

    @Override
    protected void initListeners() {
        menuSettings.setOnSuperTextViewClickListener(this);
        menuAbout.setOnSuperTextViewClickListener(this);
        rivHead.setOnSuperTextViewClickListener(this);
        zhanghao.setOnSuperTextViewClickListener(this);
        tongzhi.setOnSuperTextViewClickListener(this);
        fankui.setOnSuperTextViewClickListener(this);

    }

    @SingleClick
    @Override
    public void onClick(SuperTextView view) {
        final JSONObject[] jb = {null};


        Intent intent;
        final TCPSocket[] tcp = {null};
        switch(view.getId()) {
            case R.id.menu_settings:
                intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_about:
                openNewPage(AboutFragment.class);
                break;
            case R.id.riv_head:

                //openNewPage(headchoose.class);


                //rivHeadPic.setImageResource(R.drawable.head);
                // XToastUtils.toast("点击头部！");
                break;
            case R.id.fankui:
                intent=new Intent(getContext(), headselect.class);
                startActivity(intent);
                //rivHeadPic.setImageResource(R.drawable.head);

                break;
            case R.id.tongzhi:
                ExecutorService mThreadPool = Executors.newCachedThreadPool();
                mThreadPool.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                tcp[0] = new TCPSocket();
                                                jb[0] = tcp[0].page2_rec_txt("tongzhi", "1");


                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                
                if(flag==0)
                {
                    while(jb[0]==null);
                    st=jb[0].getString("content");
                    XToastUtils.toast(st);
                    jb[0]=null;
                    tcp[0]=null;
                    flag++;
                }else{
                    XToastUtils.toast(st);
                }
                
                //rivHeadPic.setImageResource(R.drawable.head);
                break;

            case R.id.zhanghao:
                //rivHeadPic.setImageResource(R.drawable.head);
                XToastUtils.toast("点击fankui！");
                break;
            default:
                break;
        }
    }
}
