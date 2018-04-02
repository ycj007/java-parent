package com.ycj.balance;

import java.util.List;

public interface LoadBalance {



    public  Invoker select(List<Invoker> invokerList);
}
