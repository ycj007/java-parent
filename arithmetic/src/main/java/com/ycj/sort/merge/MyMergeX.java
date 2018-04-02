package com.ycj.sort.merge;

import com.google.common.base.Stopwatch;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;
import java.util.Comparator;

public class MyMergeX {
    private static final int CUTOFF = 100;  // cutoff to insertion sort

    // This class should not be instantiated.
    private MyMergeX() { }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {

        // precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        if(less(src[mid],src[mid+1])){
            return;
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(src[j], src[i])) {
                dst[k] = src[j++];   // to ensure stability
            } else {
                dst[k] = src[i++];
            }
        }

        // postcondition: dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF) { 
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        // if (!less(src[mid+1], src[mid])) {
        //    for (int i = lo; i <= hi; i++) dst[i] = src[i];
        //    return;
        // }

        // using System.arraycopy() is a bit faster than the above loop
        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1);  
        assert isSorted(a);
    }

    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int l, int h) {
        for (int i = l+1; i <=h; i++) {

            // binary search to determine index j at which to insert a[i]
            /**
             * 先利用二分查找确定插入元素的位置
             */
            Comparable v = a[i];
            int lo = l, hi = i;
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
    }


    /*******************************************************************
     *  Utility methods.
     *******************************************************************/

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is a[i] < a[j]?
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // is a[i] < a[j]?
    private static boolean less(Object a, Object b, Comparator comparator) {
        return comparator.compare(a, b) < 0;
    }


    /*******************************************************************
     *  Version that takes Comparator as argument.
     *******************************************************************/

    /**
     * Rearranges the array in ascending order, using the provided order.
     *
     * @param a the array to be sorted
     * @param comparator the comparator that defines the total order
     */
    public static void sort(Object[] a, Comparator comparator) {
        Object[] aux = a.clone();
        sort(aux, a, 0, a.length-1, comparator);
        assert isSorted(a, comparator);
    }

    private static void merge(Object[] src, Object[] dst, int lo, int mid, int hi, Comparator comparator) {

        // precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, lo, mid, comparator);
        assert isSorted(src, mid+1, hi, comparator);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(src[j], src[i], comparator)) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }

        // postcondition: dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi, comparator);
    }


    private static void sort(Object[] src, Object[] dst, int lo, int hi, Comparator comparator) {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF) { 
            insertionSort(dst, lo, hi, comparator);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid, comparator);
        sort(dst, src, mid+1, hi, comparator);

        // using System.arraycopy() is a bit faster than the above loop
        if (!less(src[mid+1], src[mid], comparator)) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi, comparator);
    }

    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], comparator); j--) {
                exch(a, j, j - 1);
            }
        }
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length - 1, comparator);
    }

    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1], comparator)) return false;
        }
        return true;
    }

    // print array to standard output
    private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them
     * (using an optimized version of mergesort); 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(100000, 1000000);
        System.out.println(Arrays.toString(source));
        Stopwatch stopwatch = Stopwatch.createStarted();
        Arrays.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());
    }
}