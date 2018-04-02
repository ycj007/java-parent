package com.ycj.sort.quick;
/******************************************************************************
 * 1：分区 通过交换数据将数组分成 大于 a[0] 和小于a[0] 两部分，并返回此时a[0] 的 index
 *
 * 2.根据 1 步的 index 分成两次递归调用  0-index  index+1-end
 *
 * 3.d递归 结束条件 hign index <= low index
 *
 * 例子
 * source: [64, 81, 6, 61, 95, 47]
 * [61, 47, 6, 64, 95, 81]
 * [6, 47, 61, 64, 95, 81]
 * [6, 47, 61, 64, 95, 81]
 * [6, 47, 61, 64, 95, 81]
 * [6, 47, 61, 64, 95, 81]
 * [6, 47, 61, 64, 95, 81]
 * [6, 47, 61, 64, 81, 95]
 * [6, 47, 61, 64, 81, 95]
 *
 ******************************************************************************/

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;



public class Quick implements Sort {

    // This class should not be instantiated.
    private Quick() {
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

    // quicksort the subarray from a[lo] to a[hi]
    private void sort(Comparable[] a, int lo, int hi) {
        //if(isSorted(a, lo, hi)) return;

        if (hi <= lo) return;
        int j = partition(a, lo, hi);
       // System.out.println(Arrays.toString(a));
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * Rearranges the array so that {@code a[k]} contains the kth smallest key;
     * {@code a[0]} through {@code a[k-1]} are less than (or equal to) {@code a[k]}; and
     * {@code a[k+1]} through {@code a[n-1]} are greater than (or equal to) {@code a[k]}.
     *
     * @param a the array
     * @param k the rank of the key
     * @return the key of rank {@code k}
     * @throws IllegalArgumentException unless {@code 0 <= k < a.length}
     */
    public Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }

        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) {
                hi = i - 1;
            } else if (i < k) {
                lo = i + 1;
            } else {
                return a[i];
            }
        }
        return a[lo];
    }


    public static void main(String[] args) {

        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(100000, 1000000);
        System.out.println(Arrays.toString(source));
        Stopwatch stopwatch = Stopwatch.createStarted();
        Quick quick = new Quick();
        quick.sort(source);
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }

}