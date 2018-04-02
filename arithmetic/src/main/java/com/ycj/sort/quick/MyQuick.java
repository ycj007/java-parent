package com.ycj.sort.quick;

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;

public class MyQuick implements Sort<Integer> {
    @Override
    public void sort(Integer[] a) {

        quickSort(a, 0, a.length);
    }

    public void quickSort(Integer[] a, int l, int h) {
        if (l>=h) {
            return;
        }

        int resultIndex = partition(a, l, h);
        quickSort(a, l, resultIndex);
        quickSort(a, resultIndex + 1, h);


    }

    public int partition(Integer[] a, int l, int h) {

        int fIndex = l;
        int p = a[l];
        int low = l+1;
        int high = h-1;
        while(low<=high){

            if(a[low]>p){
                exch(a,low,high);
            }
            low++;
            if(a[high]<p){
                exch(a,low,high);
            }
            high--;
        }
        exch(a,fIndex,low);
        return low;

    }

    public static void main(String[] args) {

        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(10, 100);
        System.out.println(Arrays.toString(source));
        Stopwatch stopwatch = Stopwatch.createStarted();
        MyQuick quick = new MyQuick();
        quick.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

    }
}
