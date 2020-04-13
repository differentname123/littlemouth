package com.example.viewpage3.myfregment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.viewpage3.adapter.BaseFragment;
import com.example.viewpage3.R;
import com.example.viewpage3.adapter.TextAdapter;
import com.example.viewpage3.recycleview.text2;

import java.util.ArrayList;
import java.util.List;

public class Fragment_2 extends BaseFragment {


    @Override
    public View initView() {

        View view= View.inflate(mContext, R.layout.page_2,null);

        List<text2> text2List = new ArrayList();
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o1));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o2));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o3));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o4));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o1));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o5));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o2));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o3));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o5));
        text2List.add(new text2("这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介这是一个特别的美食简介",R.drawable.o4));
        TextAdapter textAdapter = new TextAdapter(text2List);


        RecyclerView recyclerView= (RecyclerView)view.findViewById(R.id.textrecyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(textAdapter);


        return view;
    }

    @Override
    public View initData() {
        return null;
    }
}
