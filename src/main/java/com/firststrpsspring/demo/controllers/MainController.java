package com.firststrpsspring.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home1(Model model) {
        model.addAttribute("title", "ShoiStore");
        return "home";
    }

    @GetMapping("/home")
    public String home2(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "about section");
        return "blog-main";
    }

    @GetMapping("/sign-in")
    public String signIN(Model model) {
        model.addAttribute("title", "about section");
        return "/sign-in";
    }
}
