package com.himax.hifood.api.controllers;

import org.springframework.stereotype.Component;

@Component
public class TestService {

    private Test test;

    public void setTest(Test s){
        test =s;
    }

    public Test getTest(){
        test.setTst(test.getTst()+1);
        return test;
    }
}
