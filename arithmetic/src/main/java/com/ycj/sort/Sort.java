package com.ycj.sort;

import java.util.Comparator;

public interface Sort<T extends Comparable > {

    void sort(T[] a);


    default boolean isSorted(T[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    default boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(comparator, a[i], a[i - 1])) return false;
        }
        return true;
    }


    // is v < w ?
    default boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    default boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    default boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    default void exch(T [] a, int i, int j) {
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * 利用 xor 无第三个数引用交换数据
     *
     * @param a
     * @param b
     */
    default void exch(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    default void show(T[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }


}
