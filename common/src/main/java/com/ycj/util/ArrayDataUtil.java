package com.ycj.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class ArrayDataUtil {


    public static Integer[] getUniqueRandomIntArray(int size, int range) {

        assert size < range;
        Integer[] finalData = new Integer[size];
        Set<Integer> data = Sets.newHashSet();
        Random random = new Random();
        while (data.size() <= size) {
            data.add(random.nextInt(range));

        }

        return data.toArray(finalData);

    }

    public static Integer[] getUniqueRandomIntArray2(int size, int range) {

        assert size < range;
        List<Integer> finalData = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            finalData.add(i + range / size);
        }
        Collections.shuffle(finalData);
        Integer[] data = new Integer[size];
        return finalData.toArray(data);

    }

    public static Integer[] merge(Integer[] a, Integer[] b) {
        Integer[] data = new Integer[a.length + b.length];
        System.arraycopy(a, 0, data, 0, a.length);
        System.arraycopy(b, 0, data, a.length, b.length);
        return data;
    }

    public static Integer[] mergeOrdered(Integer[] a, Integer[] b) {
        Integer[] data = new Integer[a.length + b.length];
        System.arraycopy(a, 0, data, 0, a.length);
        System.arraycopy(b, 0, data, a.length, b.length);
        //insertionSort(data,0,data.length-1);
        Arrays.sort(data);
        return data;
    }

    public static Integer[] mergeOrdered2(Integer[] a, Integer[] b) {
        Integer[] data = new Integer[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;

        for (int i = 0; i < data.length; i++) {
            if (aIndex < a.length && bIndex < b.length) {
                if (a[aIndex] < b[bIndex]) {

                    data[i] = a[aIndex];
                    //++i;
                    ++aIndex;
                } else if (a[aIndex] > b[bIndex]) {
                    data[i] = b[bIndex];
                    //++i;
                    ++bIndex;
                } else {
                    data[i] = a[aIndex];
                    data[++i] = b[bIndex];
                    // ++i;
                    ++aIndex;
                    ++bIndex;
                }
            } else if (aIndex < a.length && bIndex >= b.length) {
                data[i] = a[aIndex];
                //++i;
                ++aIndex;
            } else if (aIndex >= a.length && bIndex < b.length) {
                data[i] = b[bIndex];
                //++i;
                ++bIndex;
            } else if (aIndex >= a.length && bIndex >= b.length) {
                break;
            }

        }
        return data;

    }

    public static void insertionSort(Integer[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && a[j] < a[j - 1]; j--) {
                Integer temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }


    public static void main(String[] args) {
//        Integer[] a = new Integer[]{1, 3, 5, 7, 9, 12, 14, 16, 17, 19, 20};
        Integer[] a = getUniqueRandomIntArray2(100000, 1000000);
//        Integer[] b = new Integer[]{4, 6, 8, 11, 13, 15, 18, 22, 24, 26, 29};
        Integer[] b = getUniqueRandomIntArray2(100000, 1000000);
        Arrays.sort(a);
        Arrays.sort(b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        Stopwatch stopwatch = new Stopwatch();
        System.out.println(Arrays.toString(mergeOrdered2(a, b)));
        stopwatch.end();
    }


}
