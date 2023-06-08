package com.example.di.controllers;

import com.example.di.services.SayHello;

// di without spring
// setter injected
public class MyController5 {
    private SayHello sayHello;

    // setter - cause this method initialize the SayHello object
    public void setter(SayHello sayHello) {
        this.sayHello = sayHello;
    }

    public String sayHello() {
        return sayHello.sayHelloHappily();
    }
}
