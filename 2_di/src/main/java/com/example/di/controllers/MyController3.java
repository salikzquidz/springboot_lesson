package com.example.di.controllers;

import com.example.di.services.SayHello;

// di without spring
// constructor injected
public class MyController3 {
    private final SayHello sayHello;

    // constructor
    public MyController3(SayHello sayHello) {
        this.sayHello = sayHello;
    }

    public String sayHello(){
        return sayHello.sayHelloHappily();
    }
}
