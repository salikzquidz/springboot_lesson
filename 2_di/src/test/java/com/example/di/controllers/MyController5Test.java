package com.example.di.controllers;

import com.example.di.services.SayHelloImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyController5Test {
    MyController5 myController5;

    @BeforeEach
    void setUp() {
        myController5 = new MyController5();
        myController5.setter(new SayHelloImpl()); // setter injected
    }

    @Test
    void sayHello() {
        System.out.println(myController5.sayHello());
    }
}