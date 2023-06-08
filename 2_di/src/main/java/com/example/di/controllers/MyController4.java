package com.example.di.controllers;

import com.example.di.services.SayHello;

// di without spring
// property injected
public class MyController4 {
    SayHello sayHello; // property

    public String sayHello(){
        return sayHello.sayHelloHappily();
    }
}
