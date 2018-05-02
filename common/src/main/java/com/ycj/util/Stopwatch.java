package com.ycj.util;
// cherry-pick
// test
public class Stopwatch {

    private long startTime = System.currentTimeMillis();
    public Stopwatch(){

    }

    public  void end(){
        System.out.println("tim1:"+(System.currentTimeMillis()-startTime));
    }
}
