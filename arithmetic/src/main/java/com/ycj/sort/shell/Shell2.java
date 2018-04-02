package com.ycj.sort.shell;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;


public class Shell2 implements Sort<Integer> {

    @Override
    public void sort(Integer[] a) {

        int shard = 2;
        int n = a.length;

        // 2x+1 increment sequence:  1, 3, 7, 15, 31, 63,  ...
        int h = 1;
        while (h < n/shard)
            h = shard*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h);
            h /= shard;
        }
        assert isSorted(a);
    }

    private   boolean isHsorted(Integer[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }

    public static void main(String[] args) {
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(100000, 1000000);
        System.out.println(Arrays.toString(source));
        Shell2 selection = new Shell2();
        Stopwatch stopwatch = Stopwatch.createStarted();
        selection.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
