package com.pooja.junitdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMathTest {
    MyMath myMath;
    @BeforeEach
    void setup(){
        myMath=new MyMath();
    }


    @org.junit.jupiter.api.Test
    void calculateSumForThreeElementsArray() {
        assertEquals(6, myMath.calculateSum(new int[]{1,2,3}));
    }

    @org.junit.jupiter.api.Test
    void calculateSumForZeroElementsArray() {

        assertEquals(0, myMath.calculateSum(new int[]{}));
    }

    @Test
    void divide() {
        assertEquals(2,myMath.divide(10,5));
    }
}