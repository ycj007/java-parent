package com.ycj.sort.quick;
/******************************************************************************
 * 1： 从两边同时跟 a[0] 比较 ，小于a[0] 的与lt交换数据，并增加 lt 大于 a[0] 的与 gt--交换,否则 i++
 * 2: 终止条件 为 i<= gt
 * 3. 递归请求。
 *
 * 下面是一个例子
 *  source :[65, 34, 22, 9, 91, 28]
 * [65, 34, 22, 9, 91, 28]
 * [34, 22, 9, 28, 65, 91]
 * [22, 9, 28, 34, 65, 91]
 * [9, 22, 28, 34, 65, 91]
 * [9, 22, 28, 34, 65, 91]
 * [9, 22, 28, 34, 65, 91]
 * [9, 22, 28, 34, 65, 91]
 *
 *
 ******************************************************************************/

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;


public class Quick3way implements Sort {

    // This class should not be instantiated.
    private Quick3way() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {

        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray a[lo .. hi] using 3-way partitioning
    private void sort(Comparable[] a, int lo, int hi) {
        if(isSorted(a, lo, hi))
            return;
        System.out.println(Arrays.toString(a));
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
        assert isSorted(a, lo, hi);
    }


    public static void main(String[] args) {
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(5, 100);
        System.out.println(Arrays.toString(source));
        Stopwatch stopwatch = Stopwatch.createStarted();
        Quick3way quick3way = new Quick3way();
        quick3way.sort(source);
       // System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());
    }

}