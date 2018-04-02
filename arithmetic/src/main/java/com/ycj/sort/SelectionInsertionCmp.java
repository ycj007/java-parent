package com.ycj.sort;

import com.google.common.base.Stopwatch;
import com.ycj.sort.insertion.Insertion2;
import com.ycj.sort.selection.Selection;
import com.ycj.util.ArrayDataUtil;

public class SelectionInsertionCmp {

    public static void main(String[] args) {

        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(100000, 10000000);

        Selection selection = new Selection();
        Stopwatch stopwatch = Stopwatch.createStarted();
        selection.sort(source);
        System.out.println(stopwatch.elapsed()
                                    .toMillis());

        Insertion2 insertion = new Insertion2();
        Stopwatch stopwatch2 = Stopwatch.createStarted();
        insertion.sort(source);

        System.out.println(stopwatch2.elapsed()
                                    .toMillis());
    }
}
