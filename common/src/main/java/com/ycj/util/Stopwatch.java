package com.ycj.util;
//add commnt	
public class Stopwatch {

    private long startTime = System.currentTimeMillis();
    public Stopwatch(){

    }

    public  void end(){
        System.out.println("tim1:"+(System.currentTimeMillis()-startTime));
    }
}
