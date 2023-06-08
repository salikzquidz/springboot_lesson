package com.example.di.controllers;

import com.example.di.services.SayHelloImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyController3Test {
    MyController3 myController3;

    @BeforeEach
    void setUp() {
        myController3 = new MyController3(new SayHelloImpl()); // constructor injected
    }

    @Test
    void sayHello() {
        System.out.println(myController3.sayHello());
    }
}