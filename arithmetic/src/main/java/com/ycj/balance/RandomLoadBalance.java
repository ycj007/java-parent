package com.ycj.balance;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class RandomLoadBalance implements LoadBalance {


    @Override
    public Invoker select(List<Invoker> invokerList) {
        return getInvokerByWeight2(invokerList);
    }


    /**
     * 简单根据权重随机获取实例
     * <p>
     * Invoker3:500055
     * 0.500055
     * Invoker2:299661
     * 0.299661
     * Invoker1:200284
     * 0.200284
     * time:2376
     *
     * @param invokerList
     * @return
     */
    public Invoker getInvokerByWeight(List<Invoker> invokerList) {
        Objects.requireNonNull(invokerList, "invokeList is not allowed null ");
        List<String> randomList = Lists.newArrayList();

        Map<String, Invoker> hash = Maps.newHashMap();
        for (Invoker invoker : invokerList) {
            invoker.setFlag(invoker.getName());
            hash.put(invoker.getFlag(), invoker);
            for (int i = 0; i < invoker.getWeight(); i++) {
                randomList.add(invoker.getFlag());
            }
        }
        Random random = new Random();
        int bound = random.nextInt(randomList.size());
        return hash.get(randomList.get(bound));


    }

    /**
     * Invoker3:500308
     * Disconnected from the target VM, address: '127.0.0.1:60208', transport: 'socket'
     * 0.500308
     * Invoker2:300080
     * 0.30008
     * Invoker1:199612
     * 0.199612
     * time:427
     *
     * @param invokerList
     * @return
     */
    public Invoker getInvokerByWeight1(List<Invoker> invokerList) {
        Objects.requireNonNull(invokerList, "invokeList is not allowed null ");
        int sumWeight = 0;
        for (Invoker invoker : invokerList) {
            sumWeight += invoker.getWeight();
        }
        Random random = new Random();
        int bound = random.nextInt(sumWeight);
        //long bound =System.currentTimeMillis()%sumWeight;
        sumWeight = 0;
        for (Invoker invoker : invokerList) {
            sumWeight += invoker.getWeight();
            if (bound < sumWeight) {
                return invoker;
            }

        }
        return invokerList.get(0);
    }

    public Invoker getInvokerByWeight2(List<Invoker> invokerList) {
        Objects.requireNonNull(invokerList, "invokeList is not allowed null ");
        int sumWeight = 0;
        Random random = new Random();
        int bound = random.nextInt(100);
        for (Invoker invoker : invokerList) {
            sumWeight += invoker.getWeight();
            if (bound < sumWeight) {
                return invoker;
            }

        }
        return invokerList.get(0);

    }





}
