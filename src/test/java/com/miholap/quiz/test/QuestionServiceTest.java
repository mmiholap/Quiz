package com.miholap.quiz.test;

import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.services.IQuestionService;
import com.miholap.quiz.services.IQuizService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/application-config.xml" )
public class QuestionServiceTest {
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IQuizService quizService;

    @Before
    public void setUp(){
        quizService.create(new Quiz("Title","Description"));
        Quiz quiz = quizService.findById(1);
        for(int i = 0; i < 10; i++ ){
            Question question = new Question("question "+i,quiz);
            questionService.create(question);
        }
    }

    @Test
    public void testGetNRandomQuestions() throws Exception {
        int N = 5;
        assertTrue(quizService.findAll().size() == 1);
        List<Question> questions = questionService.getNRandomQuestions(quizService.findById(1),N);
        for (Question q : questions){
            System.out.println(q.toString());
        }
        assertTrue(questions.size() > 0 );
    }
}