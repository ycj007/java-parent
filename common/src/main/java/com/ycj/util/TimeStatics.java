package com.ycj.util;

public class TimeStatics {

    private long startTime = System.currentTimeMillis();
    public TimeStatics(){

    }

    public  void end(){
        System.out.println("time:"+(System.currentTimeMillis()-startTime));
    }
}
