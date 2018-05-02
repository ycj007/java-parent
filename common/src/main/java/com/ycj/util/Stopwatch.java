package com.ycj.util;
// cherry-pick
public class Stopwatch {

    private long startTime = System.currentTimeMillis();
    public Stopwatch(){

    }

    public  void end(){
        System.out.println("time:"+(System.currentTimeMillis()-startTime));
    }
}
