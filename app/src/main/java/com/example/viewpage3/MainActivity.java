package com.example.viewpage3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpage3.adapter.BaseFragment;
import com.example.viewpage3.adapter.FragmentAdapter;
import com.example.viewpage3.myfregment.Fragment_1;
import com.example.viewpage3.myfregment.Fragment_2;
import com.example.viewpage3.myfregment.Fragment_3;
import com.example.viewpage3.myfregment.Fragment_4;
import com.example.viewpage3.myfregment.Fragment_login;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;

    // 底部菜单4个菜单标题
    private TextView txt_1;
    private TextView txt_2;
    private TextView txt_3;
    private TextView txt_4;


    private LinearLayout ll_1;
    private LinearLayout ll_2;
    private LinearLayout ll_3;
    private LinearLayout ll_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();// 设置按钮监听

        restartBotton();
        txt_1.setTextColor(0xff1B940A);

        ActionBar act=getSupportActionBar();
        if(act!=null){
            act.hide();
        }

    }
    
    private void initEvent(){

        ll_1.setOnClickListener((View.OnClickListener) this);
        ll_2.setOnClickListener((View.OnClickListener) this);
        ll_3.setOnClickListener((View.OnClickListener) this);
        ll_4.setOnClickListener((View.OnClickListener) this);
        //second.setOnClickListener(this);//使用就报错
        mViewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) this);
    }

    private void initView(){

        // 底部菜单4个Linearlayout
        ll_1 = findViewById(R.id.ll_1);
        ll_2 = findViewById(R.id.ll_2);
        ll_3 = findViewById(R.id.ll_3);
        ll_4 = findViewById(R.id.ll_4);
        //second=findViewById(R.id.textView11);
        // 底部菜单4个菜单标题
        txt_1 = findViewById(R.id.tx_main_1);
        txt_2 = findViewById(R.id.tx_main_2);
        txt_3 = findViewById(R.id.tx_main_3);
        txt_4 = findViewById(R.id.tx_main_4);

        // 中间内容区域ViewPager
        mViewPager = (ViewPager)findViewById(R.id.viewpage);

        List<BaseFragment> mFragments = new ArrayList<BaseFragment>();
        mFragments.add(new Fragment_1());
        mFragments.add(new Fragment_2());
        mFragments.add(new Fragment_3());
        mFragments.add(new Fragment_4());
        //mFragments.add(new Fragment_login());

        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_1:
                restartBotton();
                txt_1.setTextColor(0xff1B940A);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ll_2:
                restartBotton();
                txt_2.setTextColor(0xff1B940A);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.ll_3:
                restartBotton();
                txt_3.setTextColor(0xff1B940A);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.ll_4:
                restartBotton();
                txt_4.setTextColor(0xff1B940A);
                mViewPager.setCurrentItem(3);
                break;



            default:
                break;
        }
    }

    private void restartBotton() {
        txt_1.setTextColor(Color.parseColor("#888888"));
        txt_2.setTextColor(Color.parseColor("#888888"));
        txt_3.setTextColor(Color.parseColor("#888888"));
        txt_4.setTextColor(Color.parseColor("#888888"));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void change1(){
        mViewPager.setCurrentItem(4);
    }
    @Override
    public void onPageSelected(int position) {

        restartBotton();
        switch (position) {
            case 0:
                txt_1.setTextColor(0xff1B940A);
                break;
            case 1:
                txt_2.setTextColor(0xff1B940A);
                break;
            case 2:
                txt_3.setTextColor(0xff1B940A);
                break;
            case 3:
                txt_4.setTextColor(0xff1B940A);
                break;

            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
