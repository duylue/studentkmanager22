package com.k22.student.controller;


import com.k22.student.config.JDBCCon;
import com.k22.student.model.Score;
import com.k22.student.model.Student;
import com.k22.student.model.Subject;
import com.k22.student.model.TypeScore;
import com.k22.student.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceIf studentServiceIf;
    @Autowired
    private ScoreServiceInterface scoreServiceInterface;




    @GetMapping
    public String home(Model model) {
        studentServiceIf.create(new Student());
        String msg = "Xin Chao";
        model.addAttribute("message", msg);
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create";
    }

    @GetMapping("/profile/{id}/{name}")
    public String detail(@PathVariable int id, @PathVariable String name, Model model) {
        System.out.println(name);
        model.addAttribute("name", name);
        model.addAttribute("id", id);
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
    public String scoreList(Model model) {
        ArrayList<Score> list = scoreServiceInterface.getList(0);
        model.addAttribute("list", list);
        return "/score/list";
    }

    @GetMapping("/score/create")
    public String createScore(Model model) {
        Score score = new Score();
        model.addAttribute(score);
        ArrayList<Student> list = StudentService.getList();
        model.addAttribute("stList", list);
        ArrayList<TypeScore> typeScores = scoreServiceInterface.getListTypeScore();
        model.addAttribute("tList", typeScores);
        ArrayList<Subject> sList = SubjService.getList();
        model.addAttribute("sList", sList);
        return "/score/scoreCreate";
    }

    @GetMapping("/score/update")
    public String updateScore(Model model, @RequestParam int id) {
        Score score = scoreServiceInterface.findScoreById(id);
        model.addAttribute(score);
        ArrayList<Student> list = StudentService.getList();
        model.addAttribute("stList", list);
        ArrayList<TypeScore> typeScores = scoreServiceInterface.getListTypeScore();
        model.addAttribute("tList", typeScores);
        ArrayList<Subject> sList = SubjService.getList();
        model.addAttribute("sList", sList);
        return "/score/update";
    }

    @PostMapping("/score/update")
    public String scUpdate(@ModelAttribute Score score) {
        scoreServiceInterface.updateScore(score);
        return "redirect:/student/score";
    }


    @PostMapping("/score/save")
    public String saveSc(@ModelAttribute Score score, @RequestParam int studentId) {
        int id = scoreServiceInterface.saveScore(score);
        scoreServiceInterface.saveScoreDetail(id, studentId);
        return "redirect:/student/score";
    }

    @GetMapping("/list")
    public String studentList(Model model) {
        ArrayList<Student> list = StudentService.getList();
        model.addAttribute("list", list);
        return "/student/list";
    }

    @GetMapping("/detail")
    public String studentDetail(Model model, @RequestParam int id) {
        ArrayList<Score> list = scoreServiceInterface.getList(id);
        model.addAttribute("list", list);
        return "/score/list";
    }
}
