package com.example.di.controllers;

import com.example.di.services.SayHelloImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyController4Test {

    MyController4 myController4;
    @BeforeEach
    void setUp() {
        myController4 = new MyController4();
        myController4.sayHello = new SayHelloImpl(); // property injected
    }

    @Test
    void sayHello() {
        System.out.println(myController4.sayHello());
    }
}