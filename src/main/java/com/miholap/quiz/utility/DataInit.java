package com.miholap.quiz.utility;

import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.services.IAnswerService;
import com.miholap.quiz.services.IQuestionService;
import com.miholap.quiz.services.IQuizService;
import com.miholap.quiz.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataInit {

    @Autowired
    private IQuizService quizService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private  IAnswerService answerService;

    //@Autowired
    //private IUserService userService;

    @PostConstruct
    public void creationFullQuizData(){
        Quiz quiz = new Quiz("Capital cities",
                "This quiz is designed for them, who want to learn the capitals of the countries " +
                        "and improve own geographical knowledge.");
        Question[] questions = {
                new Question("What is the capital of Norway?",quiz),
                new Question("What is the capital of Austria?",quiz),
                new Question("What is the capital of South Korea?",quiz),
                new Question("What is the capital of Hungary?",quiz),
                new Question("What is the capital of Mongolia?",quiz),
                new Question("What is the capital of Iceland?",quiz),
                new Question("What is the capital of Spain?",quiz),
                new Question("What is the capital of Chili?",quiz),
                new Question("What is the capital of Ukraine?",quiz),
                new Question("What is the capital of India?",quiz)
        };

        Answer[][] answers ={
                {new Answer("Oslo",questions[0],true),
                        new Answer("London",questions[0],false),
                        new Answer("Madrid",questions[0],false)},
                {new Answer("Oslo",questions[1],true),
                        new Answer("Vienna",questions[1],true),
                        new Answer("Prague",questions[1],false),
                        new Answer("Mexico",questions[1],false)},
                {new Answer("Dublin",questions[2],false),
                        new Answer("Seoul",questions[2],true),
                        new Answer("Moscow",questions[2],false),
                        new Answer("Washington",questions[2],false),
                        new Answer("Keninsberg",questions[2],false)},
                {new Answer("Prague",questions[3],true),
                        new Answer("Vienna",questions[3],false),
                        new Answer("Madrid",questions[3],false),
                        new Answer("Budapest",questions[3],true)},
                {new Answer("Ulaanbaatar ",questions[4],true),
                        new Answer("Tbilisi",questions[4],false),
                        new Answer("Baku",questions[4],false)},
                {new Answer("Madrid",questions[5],false),
                        new Answer("Brasilia",questions[5],false),
                        new Answer("Reykjavik",questions[5],true),
                        new Answer("Budapest",questions[5],false)},
                {new Answer("Oslo",questions[6],false),
                        new Answer("London",questions[6],false),
                        new Answer("Madrid",questions[6],true)},
                {new Answer("Buenos Aires",questions[7],false),
                        new Answer("Santiago",questions[7],true),
                        new Answer("Lima",questions[7],false)},
                {new Answer("Kiev",questions[8],true),
                        new Answer("Lviv",questions[8],false),
                        new Answer("Baku",questions[8],false)},
                {new Answer("Kathmandu",questions[9],false),
                        new Answer("New Delhi",questions[9],true),
                        new Answer("Taipei",questions[9],false)}
        };

        quizService.create(quiz);
        for(int i = 0; i < answers.length;i++) {
            questionService.create(questions[i]);
            for (int j = 0; j < answers[i].length; j++) {
                answerService.create(answers[i][j]);
            }
        }
    }

}
