package com.java;

import org.junit.Test;

public class Goods implements Comparable {

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods){
            Goods goods = (Goods) o;
            if(this.price > goods.price){
                return 1;
            }
            else if (this.price < goods.price) {
                return -1;
            }
            else{
                return 0;
            }
        }
        throw new RuntimeException();
    }


}
