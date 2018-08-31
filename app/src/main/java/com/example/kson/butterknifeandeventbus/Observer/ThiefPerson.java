package com.example.kson.butterknifeandeventbus.Observer;

import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/08/31
 * Description:小偷
 */
public class ThiefPerson implements IObservable {

    List<Observer> list = new ArrayList<>();
    @Override
    public void register(Observer observer) {
        if (!list.contains(observer)){//集合中是否包含观察者

            list.add(observer);
        }


    }

    @Override
    public void unRegister(Observer observer) {


        list.remove(observer);

    }

    /**
     * 被观察者通知观察者
     */
    @Override
    public void notifyMessage() {

        for (Observer observer : list) {
            String s = new String();
            s = "我是小偷，来抓我吧";//事件角色
            //观察者收到消息通知
            observer.update(s);
        }

    }
}
