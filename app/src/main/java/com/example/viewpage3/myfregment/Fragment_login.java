package com.example.viewpage3.myfregment;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.viewpage3.adapter.BaseFragment;
import com.example.viewpage3.R;

public class Fragment_login extends BaseFragment {

    @Override
    public View initView() {
        View view= View.inflate(mContext, R.layout.login,null);
        return view;

    }

    @Override
    public View initData() {
        return null;
    }
}
