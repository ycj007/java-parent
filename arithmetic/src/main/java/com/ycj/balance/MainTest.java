package com.ycj.balance;

import com.google.common.collect.Lists;
import com.ycj.util.Stopwatch;

import java.util.List;

public class MainTest {


    public static void main(String[] args) {

        List<Invoker> list = Lists.newArrayList(new Invoker(20,"Invoker1"), new Invoker(30,"Invoker2"),new Invoker(50,"Invoker3"));

        RandomLoadBalance randomLoadBalance = new RandomLoadBalance();

        Stopwatch timeStatics = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            Statics.addStatics(randomLoadBalance.select(list));
        }

        Statics.printStatics();
        timeStatics.end();


    }

}
