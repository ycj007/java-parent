package com.ycj.sort.insertion;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;

/**
 * 【插入算法】
 *  将数组分为已排好序和未排好序两部分，然后未排好序的元素依次向已排好序的数组插入。
 */

public class Insertion implements Sort<Integer> {


    /**
     * 将插入位置后的元素向后移动
     * @param a
     */
    @Override
    public void sort(Integer[] a) {
        int length = a.length;

        for (int i = 0; i < length; i++) {

            /**
             * 待插入元素
             */
            int insert = a[i];
            /**
             * 待插入位置
             */
            int index = 0;
            /**
             * 查找待插入位置
             */
            for (int j = i - 1; j > -1; j--) {
                if (insert < a[j]) {
                    index = j;
                    break;
                }
            }
            /**
             * 待插入位置后的 元素全部向后移动一位
             */
            for (int j = index; j > 0; j--) {

                a[index + 1] = a[index];
            }
            /**
             * 插入元素
             */
            a[index] = insert;

        }


    }
    public   void sort(Integer[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi);
    }
    public static void main(String[] args) {
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(10, 10000);
        System.out.println(Arrays.toString(source));
        Insertion selection = new Insertion();
        Stopwatch stopwatch = Stopwatch.createStarted();
        selection.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
