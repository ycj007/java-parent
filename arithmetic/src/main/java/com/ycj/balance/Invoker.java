package com.ycj.balance;

public class Invoker {

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String flag;
    private int weight;
    private String name;

    public Invoker(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public Invoker(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
