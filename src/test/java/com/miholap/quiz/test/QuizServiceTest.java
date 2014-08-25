package com.miholap.quiz.test;

import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.services.IQuizService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/application-config.xml" )
public class QuizServiceTest {

    @Autowired
    IQuizService quizService;



    @Test
    public void createQuiz(){
        Quiz quiz = new Quiz("Title","Description");
        int sizeBefore = quizService.findAll().size();
        quizService.create(quiz);
        int sizeAfter = quizService.findAll().size();
        assertTrue(sizeAfter - sizeBefore == 1);
    }

    @Test
    public void updateQuiz(){
        Quiz quiz = new Quiz("Title","Description");
        quizService.create(quiz);
        List<Quiz> quizs = quizService.findAll();
        for(Quiz qz : quizs){
            qz.setTitle("New Title");
            quizService.update(qz);
        }

        assertEquals( quizService.findById(1).getTitle(), "New Title");
    }
}