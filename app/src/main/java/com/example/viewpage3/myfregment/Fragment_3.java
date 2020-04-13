package com.example.viewpage3.myfregment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.viewpage3.adapter.BaseFragment;
import com.example.viewpage3.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_3 extends BaseFragment {

    @Override
    public View initView() {
        View view= View.inflate(mContext, R.layout.page_3,null);
        int[] imageid=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,
                R.drawable.img4, R.drawable.img5,R.drawable.img6,R.drawable.img7,
                R.drawable.img8};
        String[] title=new String[]{
                "彼岸花","塞北无言","離","杜雨笙","木木夕","团子大家族","白目","摆摆不是条鱼"
        };
        List<Map<String,Object>> listitem=new ArrayList<Map<String, Object>>();
        for (int i=0;i<imageid.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",imageid[i]);
            map.put("name",title[i]);
            listitem.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(getContext(),listitem,R.layout.list_item3,
                new String[]{"name","image"},new int[]{R.id.title,R.id.image});
        ListView listView=view.findViewById(R.id.listview);

        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public View initData() {
        return null;
    }
}
