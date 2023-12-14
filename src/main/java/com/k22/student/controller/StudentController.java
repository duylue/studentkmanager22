package com.k22.student.controller;


import com.k22.student.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public String home(Model model) {
        String msg = "Xin Chao";
        model.addAttribute("message", msg);
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Student student = new Student();
        model.addAttribute("student",student);
        return "create";
    }

    @GetMapping("/profile/{id}/{name}")
    public String detail(@PathVariable int id, @PathVariable String name, Model model) {
        System.out.println(name);
        model.addAttribute("name",name);
        model.addAttribute("id",id);
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
    public String save(@ModelAttribute Student student) {
        System.out.println(student);
        return "redirect:/student/comm";
    }

}
