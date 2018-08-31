package com.example.kson.butterknifeandeventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kson.butterknifeandeventbus.eventbus.UserBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.user_btn)
    Button userBtn;

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
        EventBus.getDefault().register(this);//注册eventbus，在哪个类里注册事件，就在哪个类接收
//        EventBus.getDefault().post("我是1");//普通事件
        EventBus.getDefault().postSticky("我是1");//粘性事件
    }

    /**
     * 收到main2发过来的消息，通过注解接收
     * @param msg
     */
    @Subscribe(priority = 50)
    public void receiveMsg(String msg){
        tv.setText(msg+"1");
        System.out.println("msg:1");
    }
    /**
     * 收到main2发过来的消息，通过注解接收
     * @param msg
     */
    @Subscribe(priority = 100)
    public void receiveMsg1(String msg){
        tv.setText(msg+"2");
        System.out.println("msg:2");
        EventBus.getDefault().cancelEventDelivery(msg);
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveBean(UserBean userBean){
        //线程
        System.out.println("threadname2:"+Thread.currentThread().getName());

//        Toast.makeText(this, ""+userBean.getName(), Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.user_btn)
    public void click(View view){

        Button button = (Button) view;
        System.out.println(button.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                //网络请求
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //得到数据
                UserBean userBean = new UserBean();
                userBean.setName("kson");
                userBean.setAge(100);

                EventBus.getDefault().post(userBean);
                System.out.println("threadname1:"+Thread.currentThread().getName());


            }
        }).start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();//解除绑定，作用，在销毁的生命周期方法里，对一切资源进行回收，优化内存，提高性能
        }
        EventBus.getDefault().unregister(this);
    }
}
