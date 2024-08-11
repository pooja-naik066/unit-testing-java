package com.pooja.junitdemo;

public class MyMath {
    int sum=0;
    public int calculateSum(int[] numbers){
     for(int number:numbers){
         sum+=number;
     }
      return sum;
    }

    public int divide(int x,int y){
        return x/y;
    }
}
