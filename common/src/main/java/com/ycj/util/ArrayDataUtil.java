package com.ycj.util;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class ArrayDataUtil {


    public static Integer[] getUniqueRandomIntArray(int size, int range) {

        assert size < range;
        Integer[] finalData = new Integer[size];
        Set<Integer> data = Sets.newHashSet();
        Random random = new Random( );
        while (data.size() <= size) {
            data.add(random.nextInt(range));
        }

        return data.toArray(finalData);

    }


}
