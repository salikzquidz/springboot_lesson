package com.example.di.controllers;

import com.example.di.services.SayHello;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String sayHello(){
        System.out.println("In controller");
        return "Hello World";
    }
}
