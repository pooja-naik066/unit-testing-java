package com.pooja.junitdemo;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyAnnotationsTest {

    @Test
    void test1(){
        System.out.println("TEST 1");
    }

    @Test
    void test2(){
        System.out.println("TEST 2");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("BEFORE EACH");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("BEFORE All");
    }

    @AfterEach
    void afterEach(){
        System.out.println("AFTER EACH");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("AFTER All");
    }


}
