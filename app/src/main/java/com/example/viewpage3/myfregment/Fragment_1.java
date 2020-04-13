package com.example.viewpage3.myfregment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpage3.adapter.BaseFragment;
import com.example.viewpage3.R;
import com.example.viewpage3.recycleview.ItemAdapter;
import com.example.viewpage3.recycleview.My_item;

import java.util.ArrayList;
import java.util.List;

public class Fragment_1 extends BaseFragment {
//    private String[] data={"张三","李四","王五","赵六","陈浮生","陈富贵","竹叶青","陈龙象","陈半仙","王虎胜","张三千"};

    private RecyclerView recyclerView;
    private List<My_item> my_items=new ArrayList<>();

    @Override
    public View initView() {

        View view= View.inflate(mContext, R.layout.page_1,null);
        initList();
/*  listview
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

 */

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapt=new ItemAdapter(my_items);
        recyclerView.setAdapter(adapt);



        return view;
    }

    @Override
    public View initData() {
        return null;
    }
    private void initList(){

        My_item apple = new My_item("Apple");
        my_items.add(apple);
        My_item banana = new My_item("Banana");
        my_items.add(banana);
        My_item cherry = new My_item("Cherry");
        my_items.add(cherry);
        My_item grape = new My_item("Grape");
        my_items.add(grape);
        My_item mango = new My_item("Mango");
        my_items.add(mango);
        My_item orange = new My_item("Orange");
        my_items.add(orange);
        My_item pear = new My_item("Pear");
        my_items.add(pear);
        My_item pineapple = new My_item("Pineapple");
        my_items.add(pineapple);
        My_item strawberry = new My_item("Strawberry");
        my_items.add(strawberry);
        My_item watermelon = new My_item("Watermelon");
        my_items.add(watermelon);
    }
}
