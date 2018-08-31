package com.example.kson.butterknifeandeventbus.Observer;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/08/31
 * Description:
 */
public class PoliceMan implements Observer {
    private String name;
    public PoliceMan(String name) {
        this.name = name;


    }

    @Override
    public void update(String msg) {

        System.out.println(name+"警察收到被抓请求："+msg);

    }
}
