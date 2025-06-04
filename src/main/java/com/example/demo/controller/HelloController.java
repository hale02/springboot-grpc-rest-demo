package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HelloController {
    @GetMapping("/api/hello")
    public String sayHello(@RequestParam(defaultValue = "bạn") String name) {
        return "Xin chào " + name + " từ REST API!";
    }
}