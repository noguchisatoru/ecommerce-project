package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to Our E-Commerce Store");
        return "home";
    }
    
    @GetMapping("/signin")
    public String login() {
        return "redirect:/login";
    }
    
    @GetMapping("/signup")
    public String register() {
        return "redirect:/register";
    }
}
