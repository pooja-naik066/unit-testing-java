package com.pooja.mockito.mockito_demo.business;

import org.springframework.beans.factory.annotation.Autowired;

public class SomeBusinessImpl {
    private DataService dataService;

    @Autowired
    public SomeBusinessImpl(DataService dataService){
        this.dataService=dataService;
    }

    public int findGreatestElement() {
    int[] data = dataService.retrieveData();
    int greatestValue=Integer.MIN_VALUE;
    for(int element:data){
        if(element>greatestValue){
            greatestValue=element;
        }

    }
   return  greatestValue;
}

}

interface DataService{
    int[] retrieveData();
}

