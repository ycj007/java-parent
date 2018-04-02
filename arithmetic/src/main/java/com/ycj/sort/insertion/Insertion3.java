package com.ycj.sort.insertion;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;

/**
 * 【插入算法】
 * 将数组分为已排好序和未排好序两部分，然后未排好序的元素依次向已排好序的数组插入。
 */

public class Insertion3 implements Sort<Integer> {


    /**
     * 将待插入元素 与已排好序的数组从后面开始，依次比较，
     * 如果，待插入元素比已排好序元素小的时候 交换，
     *
     * @param a
     */
    @Override
    public void sort(Integer[] a) {
        int n = a.length;

        // put smallest element in position to serve as sentinel

        /**
         * 从末尾开始交换将最小元素移动到第一个位置；
         * 如果exchanges=0 说明本身是有序的无须排序
         */
        int exchanges = 0;
        for (int i = n-1; i > 0; i--) {
            if (less(a[i], a[i-1])) {
                exch(a, i, i-1);
                exchanges++;
            }
        }
        if (exchanges == 0) return;


        /**
         * 从第3个位置开始插入排序 index=2
         */
        // insertion sort with half-exchanges
        for (int i = 2; i < n; i++) {
            Integer v = a[i];
            int j = i;
            while (less(v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }

        //assert isSorted(a);


    }

    public static void main(String[] args) {
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(100000, 10000000);
        System.out.println(Arrays.toString(source));
        Insertion3 selection = new Insertion3();
        Stopwatch stopwatch = Stopwatch.createStarted();
        selection.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
