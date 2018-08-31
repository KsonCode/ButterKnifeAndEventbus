package com.example.kson.butterknifeandeventbus.Observer;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/08/31
 * Description:抽象观察者
 */
public interface Observer {

    void update(String msg);//收到被观察者的消息，并消费（使用）
}
