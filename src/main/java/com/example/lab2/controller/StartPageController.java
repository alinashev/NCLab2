package com.example.lab2.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StartPageController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title","Currency Agregator");
        return "index";
    }
}