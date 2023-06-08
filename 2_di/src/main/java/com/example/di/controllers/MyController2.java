package com.example.di.controllers;

import com.example.di.services.SayHello;
import com.example.di.services.SayHelloImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController2 {
    private final SayHello sayHello;

    public MyController2() {
        this.sayHello = new SayHelloImpl();
    }

    public String sayHello(){
        return sayHello.sayHelloHappily();
    }
}
