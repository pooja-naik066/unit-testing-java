package com.pooja.mockito.mockito_demo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeBusinessImplStubTest {

    @Test
    void findGreatestElement() {
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusinessImpl someBusiness = new SomeBusinessImpl(dataServiceStub);
        int result = someBusiness.findGreatestElement();
        assertEquals(3, result);
    }
}
    class DataServiceStub implements DataService{

        @Override
        public int[] retrieveData() {
            return new int[]{1,2,3};
        }
    }

