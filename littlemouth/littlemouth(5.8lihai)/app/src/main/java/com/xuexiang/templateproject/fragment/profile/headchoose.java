package com.xuexiang.templateproject.fragment.profile;/*
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


import android.content.Intent;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.LoginActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-10-15 22:38
 */
@Page(name = "choose")
public class headchoose extends BaseFragment implements SuperTextView.OnSuperTextViewClickListener {

    @BindView(R.id.menu_common)
    SuperTextView menu_common;
    @BindView(R.id.riv_head_pic)
    RadiusImageView head;


    @Override
    protected int getLayoutId() {
        return R.layout.headchoose;
    }

    @Override
    protected void initViews() {
        menu_common.setOnSuperTextViewClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(SuperTextView superTextView) {
        switch(superTextView.getId()) {
            case R.id.menu_common:
                head.setImageResource(R.drawable.head);
                break;
            case R.id.menu_privacy:
            case R.id.menu_push:
            case R.id.menu_helper:
                XToastUtils.toast(superTextView.getLeftString());
                break;
            case R.id.menu_change_account:
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_logout:
                XToastUtils.toast(superTextView.getCenterString());
                break;
            default:
                break;
        }
    }
}
