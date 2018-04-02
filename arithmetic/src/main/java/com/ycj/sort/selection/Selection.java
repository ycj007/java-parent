package com.ycj.sort.selection;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;


/**
 * 【选择排序】
 * 首先查找数组中最小元素 min
 * 将min 与数组第一个元素交换
 * 然后在剩下的元素中查找最小元素再与第二个元素交换
 * 如此往复
 */
public class Selection implements Sort<Integer> {

    @Override
    public void sort(Integer[] a) {

        int length = a.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            /**
             *  假设i 为最小 查找 i 以后的的最小 元素 j ,然后交换i,j 代表的元素
             */
            for (int j = i + 1; j < length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
    }

    public static void main(String[] args) {
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(100000, 1000000);
        System.out.println(Arrays.toString(source));
        Selection selection = new Selection();
        Stopwatch stopwatch = Stopwatch.createStarted();
        selection.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
