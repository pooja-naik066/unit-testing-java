package com.pooja.junitdemo;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyAssertTest {

    List<String> todos= Arrays.asList("AWS","AZURE","GOOGLE");


    @org.junit.jupiter.api.Test
    void testAssert() {
        assertEquals(true,todos.contains("AWS"));
        assertTrue(todos.contains("GOOGLE"));
        assertArrayEquals(new int[]{1,2},new int[]{1,2});


    }

}
