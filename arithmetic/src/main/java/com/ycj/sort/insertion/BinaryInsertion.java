package com.ycj.sort.insertion;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;

/**
 * 【插入算法】
 * 将数组分为已排好序和未排好序两部分，然后未排好序的元素依次向已排好序的数组插入。
 */

public class BinaryInsertion implements Sort<Integer> {


    /**
     * 将待插入元素 与已排好序的数组从后面开始，依次比较，
     * 如果，待插入元素比已排好序元素小的时候 交换，
     *
     * @param a
     */
    @Override
    public void sort(Integer[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {

            // binary search to determine index j at which to insert a[i]
            /**
             * 先利用二分查找确定插入元素的位置
             */
            Integer v = a[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (less(v, a[mid])) hi = mid;
                else                 lo = mid + 1;
            }

            // insetion sort with "half exchanges"
            // (insert a[i] at index j and shift a[j], ..., a[i-1] to right)
            /**
             * 根据先前找到的位置直接插入数据
             */
            for (int j = i; j > lo; --j)
                a[j] = a[j-1];
            a[lo] = v;
        }
        assert isSorted(a);


    }

    public static void main(String[] args) {

        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(100000, 10000000);
        System.out.println(Arrays.toString(source));
        BinaryInsertion selection = new BinaryInsertion();
        Stopwatch stopwatch = Stopwatch.createStarted();
        selection.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
