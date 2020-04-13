package com.example.viewpage3.myfregment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpage3.adapter.BaseFragment;
import com.example.viewpage3.LoginActivity;
import com.example.viewpage3.R;

public class Fragment_4 extends BaseFragment {



    @Override
    public View initView() {

       View view= View.inflate(mContext, R.layout.page_4,null);

        TextView tex=(TextView)view.findViewById(R.id.textView11);
        tex.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

//                ((MainActivity)getActivity()).change1();
            }
        });

        ImageView jilu=view.findViewById(R.id.imageView11);
        jilu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "别点我 没结果",
                        Toast.LENGTH_SHORT).show();

            }
        });

        ImageView sc=view.findViewById(R.id.imageView111);
        sc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "别点我 没结果",
                        Toast.LENGTH_SHORT).show();

            }
        });


        ImageView set=view.findViewById(R.id.imageView13);
        set.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "别点我 没结果",
                        Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    @Override
    public View initData() {
        return null;
    }
}
