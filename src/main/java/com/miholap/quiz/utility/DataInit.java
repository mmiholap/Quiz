package com.miholap.quiz.utility;

import com.miholap.quiz.persistence.entities.*;
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

    @Autowired
    private IUserService userService;

    @PostConstruct
    public void creationFullQuizData(){
        User user = new User("anonymous","", Role.USER,"");
        userService.create(user);
        dataForCapitalsQuiz();
        dataForFootbalQuiz();
    }

    public void dataForFootbalQuiz(){
        Quiz quiz = new Quiz("UEFA Champions League",
                "This European soccer league has been founded more than 50 years ago. It consists of many great teams like " +
                        "FC Barcelona, Bayern Munich, etc. Let's see how much you know about this league");
        quiz.setImagePath("/resources/images/uefaFootbal.jpg");

        Question[] questions = {
                new Question("Which soccer team has won the most UEFA Champions league titles?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("Which year was the Uefa Champions League founded in?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("A total of how many teams have won the champions league?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("Which 2 players have scored 67 or 68 goals in UEFA Champions league history?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("Who won the 2013-2014 Champions league finals?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("Which team beat PSG in the quarter finals of the 2013-2014 Champions league season?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("What is the highest goal margin for one game?(difference between 2 teams' goals.)","/resources/images/uefaFootbal.jpg",quiz),
                new Question("Who is the only player in the UEFA Champions League to have scored 4 hat tricks?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("Which team is the only team in Uefa champions league to have scored 5 points yet advanced from qualifying?","/resources/images/uefaFootbal.jpg",quiz),
                new Question("The fastest goal from kick off in the Champions League was scored in- ","/resources/images/uefaFootbal.jpg",quiz)
        };

        Answer[][] answers ={
                {new Answer("Real Madrid",questions[0],true),
                        new Answer("FC Barcelona",questions[0],false),
                        new Answer("Chelsea",questions[0],false),
                        new Answer("Milan",questions[0],false)},
                {new Answer("1928",questions[1],false),
                        new Answer("1989",questions[1],false),
                        new Answer("1932",questions[1],false),
                        new Answer("1955",questions[1],true),
                        new Answer("1957",questions[1],false)},
                {new Answer("28",questions[2],false),
                        new Answer("18",questions[2],false),
                        new Answer("22",questions[2],true),
                        new Answer("32",questions[2],false),
                        new Answer("34",questions[2],false)},
                {new Answer("Wayne Rooney and Frank Lampard",questions[3],false),
                        new Answer("Andriy Shevchenko and Didier Drogba",questions[3],false),
                        new Answer("Cristiano Ronaldo and Didier Drogba",questions[3],false),
                        new Answer("Cristiano Ronaldo and Lionel Messi",questions[3],true)},
                {new Answer("Real Madrid ",questions[4],true),
                        new Answer("FC Barcelona",questions[4],false),
                        new Answer("Athletico Madrid",questions[4],false)},
                {new Answer("Manchester United",questions[5],false),
                        new Answer("Athletico Madrid",questions[5],false),
                        new Answer("Chelsea",questions[5],true),
                        new Answer("Bayern Munich",questions[5],false)},
                {new Answer("9",questions[6],false),
                        new Answer("7",questions[6],false),
                        new Answer("11",questions[6],true)},
                {new Answer("Cristiano Ronaldo",questions[7],false),
                        new Answer("Lionel Messi",questions[7],true),
                        new Answer("Frank Lampard",questions[7],false)},
                {new Answer("Milan",questions[8],true),
                        new Answer("Fc Barcelona",questions[8],false),
                        new Answer("Bayer Leverkusen",questions[8],false)},
                {new Answer("1 minute 32 seconds",questions[9],false),
                        new Answer("10 seconds",questions[9],true),
                        new Answer("6 seconds",questions[9],false),
                        new Answer("22 seconds",questions[9],false)}

        };

        quizService.create(quiz);
        for(int i = 0; i < answers.length;i++) {
            questionService.create(questions[i]);
            for (int j = 0; j < answers[i].length; j++) {
                answerService.create(answers[i][j]);
            }
        }
    }

    public void dataForCapitalsQuiz(){

        Quiz quiz = new Quiz("Capital cities",
                "This quiz is designed for them, who want to learn the capitals of the countries " +
                        "and improve own geographical knowledge.");
        quiz.setImagePath("/resources/images/capitalsQuiz.jpg");//

        Question[] questions = {
                new Question("What is the capital of Norway?","/resources/images/norway.jpg",quiz),
                new Question("What is the capital of Austria?","/resources/images/austria.jpg",quiz),
                new Question("What is the capital of South Korea?","/resources/images/south_korea.jpg",quiz),
                new Question("What is the capital of Hungary?","/resources/images/hungary.jpg",quiz),
                new Question("What is the capital of Mongolia?","/resources/images/mongolia.jpg",quiz),
                new Question("What is the capital of Iceland?","/resources/images/iceland.jpg",quiz),
                new Question("What is the capital of Spain?","/resources/images/spain.jpg",quiz),
                new Question("What is the capital of Chile?","/resources/images/chile.jpg",quiz),
                new Question("What is the capital of Ukraine?","/resources/images/ukraine.jpg",quiz),
                new Question("What is the capital of India?","/resources/images/india.jpg",quiz)
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
