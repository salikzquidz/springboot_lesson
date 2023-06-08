package com.example.di.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyController2Test {
    // dependency without injection

    @Test
    void sayHello(){
        MyController2 myController2 = new MyController2();
        System.out.println(myController2.sayHello());
    }
}