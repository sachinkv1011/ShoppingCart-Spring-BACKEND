package com.example.shoppingCart_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {


    @CrossOrigin(origins="*")
    @GetMapping("/home")
    public String homePage(){
        return "Welcome to Home page of shopping cart";
    }

}
