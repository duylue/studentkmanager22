package com.k22.student.controller;


import com.k22.student.config.JDBCCon;
import com.k22.student.model.Score;
import com.k22.student.model.Student;
import com.k22.student.model.Subject;
import com.k22.student.model.TypeScore;
import com.k22.student.service.ScoreService;
import com.k22.student.service.StudentService;
import com.k22.student.service.SubjService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    @GetMapping("/score")
    public String scoreList(Model model){
        ArrayList<Score> list = ScoreService.getList();
        model.addAttribute("list",list);
        return "/score/list";
    }
    @GetMapping("/score/create")
    public String createScore(Model model){
        Score score = new Score();
        model.addAttribute(score);
        ArrayList<Student> list = StudentService.getList();
        model.addAttribute("stList",list);
        ArrayList<TypeScore> typeScores = ScoreService.getListTypeScore();
        model.addAttribute("tList",typeScores);
        ArrayList<Subject> sList = SubjService.getList();
        model.addAttribute("sList",sList);

        return "/score/scoreCreate";
    }
    @PostMapping("/score/save")
    public String saveSc(@ModelAttribute Score score , @RequestParam int studentId) {
        System.out.println(studentId);
        return "redirect:/student/comm";
    }

}
