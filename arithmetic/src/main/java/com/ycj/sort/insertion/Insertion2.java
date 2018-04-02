package com.ycj.sort.insertion;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;

/**
 * 【插入算法】
 * 将数组分为已排好序和未排好序两部分，然后未排好序的元素依次向已排好序的数组插入。
 */

public class Insertion2 implements Sort<Integer> {


    /**
     * 将待插入元素 与已排好序的数组从后面开始，依次比较，
     * 如果，待插入元素比已排好序元素小的时候 交换，
     *
     * @param a
     */
    @Override
    public void sort(Integer[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            int insert = a[i];
            int insertIndex = i;
            /**
             * 遍历已排好序数组与插入元素比较
             */
            for (int j = i-1; j >=0; j--) {
                if(less(insert,a[j])){
                    exch(a,insertIndex,j);
                    insertIndex=j;
                }
            }

        }


    }

    public static void main(String[] args) {
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(10, 10000);
        System.out.println(Arrays.toString(source));
        Insertion2 selection = new Insertion2();
        Stopwatch stopwatch = Stopwatch.createStarted();
        selection.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
