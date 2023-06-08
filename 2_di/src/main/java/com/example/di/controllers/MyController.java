package com.example.di.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String sayHello(){
        System.out.println("In controller");
        return "Hello World";
    }
}
