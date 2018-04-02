package com.ycj.sort.quick; /******************************************************************************
 *  Compilation:  javac Quick3way.java
 *  Execution:    java Quick3way < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                https://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using 3-way quicksort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Quick3way < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Quick3way < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 ******************************************************************************/

import com.google.common.base.Stopwatch;
import com.ycj.sort.Sort;
import com.ycj.util.ArrayDataUtil;

import java.util.Arrays;

/**
 * The {@code Quick3way} class provides static methods for sorting an
 * array using quicksort with 3-way partitioning.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/23quick">Section 2.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
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
        Integer[] source = ArrayDataUtil.getUniqueRandomIntArray(10, 100);
        System.out.println(Arrays.toString(source));
        Stopwatch stopwatch = Stopwatch.createStarted();
        Quick3way quick3way = new Quick3way();
        quick3way.sort(source);
        System.out.println(Arrays.toString(source));
        System.out.println(stopwatch.elapsed()
                                    .toMillis());
    }

}