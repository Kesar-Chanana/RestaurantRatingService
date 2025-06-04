package com.bounteous.solid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class TestControlller {

    @GetMapping("/cust")
    public List<String> getCustomer(@RequestParam(name= "name") String name) {

        System.out.println("Inside Test Controller");
        System.out.println("name" + name);

        return Arrays.asList("Banana", "Grapes", "Apple", "Orange", "Cherry");
    }
}
