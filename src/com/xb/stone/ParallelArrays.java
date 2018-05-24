package com.xb.stone;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 并行数组.
 *
 * @author xiaobo.qin
 * @time 2018/5/24 9:36
 * @since JDK 1.8
 */
public class ParallelArrays {

    public static void main( String[] args ) {
        long[] arrayOfLong = new long [ 20000 ];
        Set<Thread> set = new HashSet<>();
        Arrays.parallelSetAll( arrayOfLong,
                index -> { set.add(Thread.currentThread());
            return ThreadLocalRandom.current().nextInt( 1000000 ); });

        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();

        Arrays.parallelSort( arrayOfLong );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println("count thread is : "+set.size());
    }
}
