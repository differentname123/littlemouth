package com.example.viewpage3.adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //获取上下文操作

        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View curentView = initView();
        return curentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();//初始化数据
        super.onActivityCreated(savedInstanceState);
    }

    /**抽象方法，子类调用*/
    public abstract View initView();

    public abstract View initData();
}
