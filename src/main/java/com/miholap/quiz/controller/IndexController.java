package com.miholap.quiz.controller;

import com.miholap.quiz.services.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {
    @Autowired
    IQuizService quizService;

    @RequestMapping(value="/index")
    public String quizInfo(Model model){
        model.addAttribute("quizs",quizService.findAll());
        return "index";
    }
}
