package com.ycj.sort.quick;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;
import org.springframework.util.Assert;

import java.util.Arrays;

public class MyQuick3way implements Sort<Integer> {
    @Override
    public void sort(Integer[] a) {

        quickSort(a, 0, a.length - 1);
    }

    public void quickSort(Integer[] a, int l, int h) {
        if (l >= h) {
            return;
        }

        int resultIndex = partition(a, l, h);
        quickSort(a, l, resultIndex);
        quickSort(a, resultIndex + 1, h);

    }

    public int partition(Integer[] a, int l, int h) {

        int p = a[l];

        int low = l + 1, high = h;
        while (low <= high) {
            if (less(a[low], p)) {
                ++low;
            } else if (less(p, a[low])) {
                exch(a, low, high);
                --high;

            } else {
                ++low;
            }
        }
        exch(a, l, high);
        return high;

    }

    public static void main(String[] args) {

        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(1000, 10000);
        System.out.println(Arrays.toString(source));
        Stopwatch stopwatch = Stopwatch.createStarted();
        MyQuick3way quick = new MyQuick3way();
        quick.sort(source);
        Assert.isTrue(quick.isSorted(source),"数组不是有序");
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
