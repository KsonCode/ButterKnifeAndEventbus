package com.example.kson.butterknifeandeventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder ;

    @BindView(R.id.tv)
    TextView tv;//不能使用private和static修饰
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initView();


        initData();



    }

    private void initView() {
//        tv = ButterKnife.findById(this,R.id.tv);
    }

    private void initData() {
        tv.setText("新数据");
//        EventBus.getDefault().register(this);//注册eventbus，在哪个类里注册事件，就在哪个类接收
//        EventBus.getDefault().post("我是1");//普通事件
        EventBus.getDefault().postSticky("我是1");//粘性事件
    }

    /**
     * 收到main2发过来的消息，通过注解接收
     * @param msg
     */
    @Subscribe
    public void receiveMsg(String msg){
        tv.setText(msg);
    }

    /**
     * 原理：在编译期间注入view，把view的初始化、监听事件进行绑定，通过anntationProccess处理器，在编译期间
     * 生成新的一个类，tv=findviewbyid(r.id.tv);
     * @param view
     */
    @OnClick({R.id.btn1,R.id.btn2})
    public void b(View view){//此方法名字是任意的
//        Toast.makeText(this, "btn1+BTN2", Toast.LENGTH_SHORT).show();

//        startActivityForResult(new Intent(this,Main2Activity.class),10);


        startActivity(new Intent(this,Main2Activity.class));
    }
//    @OnClick(R.id.btn2)
//    public void b1(View view){//此方法名字是任意的
//        Toast.makeText(this, "btn2", Toast.LENGTH_SHORT).show();
//
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();//解除绑定，作用，在销毁的生命周期方法里，对一切资源进行回收，优化内存，提高性能
        }
        EventBus.getDefault().unregister(this);
    }
}
