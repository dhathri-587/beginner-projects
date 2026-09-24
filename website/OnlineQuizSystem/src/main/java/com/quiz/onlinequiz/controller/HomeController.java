package com.quiz.onlinequiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/shanthi-dharma-quiz")
    public String home() {
        return "index";
    }
}