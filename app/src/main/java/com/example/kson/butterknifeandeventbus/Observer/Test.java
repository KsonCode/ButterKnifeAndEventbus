package com.example.kson.butterknifeandeventbus.Observer;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/08/31
 * Description:
 */
public class Test {
    public static void main(String args[]){
        //被观察者角色：发布者
        IObservable thiefPerson = new ThiefPerson();
        //观察者模式：订阅者
        PoliceMan policeMan = new PoliceMan("张安");
        PoliceMan policeMan2 = new PoliceMan("张安2");
        PoliceMan policeMan3 = new PoliceMan("张安3");
        PoliceMan policeMan4 = new PoliceMan("张安4");
        PoliceMan policeMan5 = new PoliceMan("张安5");
        PoliceMan policeMan6 = new PoliceMan("张安6");
        PoliceMan policeMan7 = new PoliceMan("张安7");
        thiefPerson.register(policeMan);
        thiefPerson.register(policeMan2);
        thiefPerson.register(policeMan3);
        thiefPerson.register(policeMan4);
        thiefPerson.register(policeMan5);
        thiefPerson.register(policeMan6);
        thiefPerson.register(policeMan7);

        //我开始偷了
        thiefPerson.notifyMessage();//通知给警察
    }
}
