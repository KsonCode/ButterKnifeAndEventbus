package com.example.kson.butterknifeandeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EventBus.getDefault().post("我是从2返回的数据");

        EventBus.getDefault().register(this);

    }

    @Subscribe(sticky = true)//注解的值，是否支持粘性事件
    public void main1(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
