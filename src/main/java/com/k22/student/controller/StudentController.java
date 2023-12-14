package com.k22.student.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/profile/{id}/{name}")
    public String detail(@PathVariable int id, @PathVariable String name) {
        System.out.println(name);
        return "detail";
    }


    @GetMapping("/comm")
    public String comm() {
        return "comm/test";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int x, @RequestParam String y) {
        System.out.println(y + x);
        return "update";
    }


    @PostMapping("/save")
    public String save(@RequestParam String name, @RequestParam int age) {
        System.out.println(name + age);
        return "redirect:/student/comm";
    }

}
