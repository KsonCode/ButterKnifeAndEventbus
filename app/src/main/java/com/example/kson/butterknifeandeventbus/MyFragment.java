package com.example.kson.butterknifeandeventbus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/08/31
 * Description:
 */
public class MyFragment extends Fragment {
    private Unbinder unbinder;

    View mRootView;
    @BindView(R.id.tv)
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView==null){
            mRootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout,container,false);
            unbinder = ButterKnife.bind(this,mRootView);
            initData();
        }

        return mRootView;
    }

    private void initData() {

        tv.setText("fragment");
    }
}
