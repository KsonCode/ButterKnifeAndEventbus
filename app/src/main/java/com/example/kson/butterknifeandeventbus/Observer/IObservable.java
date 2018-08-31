package com.example.kson.butterknifeandeventbus.Observer;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/08/31
 * Description:抽象被观察者
 */
public interface IObservable {
    void register(Observer observer);//订阅
    void unRegister(Observer observer);//取消订阅
    void notifyMessage();//通知观察者接收消息

}
