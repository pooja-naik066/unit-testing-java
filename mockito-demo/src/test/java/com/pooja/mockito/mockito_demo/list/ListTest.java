package com.pooja.mockito.mockito_demo.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    void basicTest(){
        List listMock= mock(List.class);
        when(listMock.size()).thenReturn(3);
        assertEquals(3,listMock.size());
    }

    @Test
    void multipleReturns(){
        List listMock= mock(List.class);
        when(listMock.size()).thenReturn(3).thenReturn(4);
        assertEquals(3,listMock.size());
        assertEquals(4,listMock.size());
        assertEquals(4,listMock.size());
    }

    @Test
    void specificParameters(){
        List listMock= mock(List.class);
        when(listMock.get(0)).thenReturn("Some String");
        assertEquals("Some String",listMock.get(0));
        assertEquals(null,listMock.get(1));
    }

    @Test
    void genericParameters(){
        List listMock= mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("Some String");
        assertEquals("Some String",listMock.get(0));
        assertEquals("Some String",listMock.get(1));
    }



}
