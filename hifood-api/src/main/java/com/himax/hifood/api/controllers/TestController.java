package com.himax.hifood.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private TestService service;

    @GetMapping("/set")
    public void test1(@RequestBody Test t){
        service.setTest(t);
    }

    @GetMapping("/get")
    public Test test2(){
        return service.getTest();
    }


}
