package com.miholap.quiz.controller;

import com.miholap.quiz.componentBeans.QuizMB;
import com.miholap.quiz.services.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Scope("request")
public class IndexController {
    @Autowired
    IQuizService quizService;
    @Autowired
    QuizMB quizManager;

    @RequestMapping(value="/index")
    public String quizInfo(Model model){
        model.addAttribute("quizs",quizService.findAll());
        return "index";
    }
}
