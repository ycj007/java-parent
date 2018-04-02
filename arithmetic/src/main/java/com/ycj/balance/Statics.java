package com.ycj.balance;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statics {


    private static List<Invoker> list = Lists.newArrayList();

    public static void printStatics() {

        Float all = Float.valueOf(list.size());
        Map<String,List<Invoker>> data = list.stream()
                       .collect(Collectors.groupingBy(d -> d.getName()));
        data.forEach((k, v) -> {
            System.out.println(k + ":" + v.size());
            System.out.println((float)(v.size())/all);
        });

    }

    public static void addStatics(Invoker invoker) {

        list.add(invoker);

    }
}
