package com.miholap.quiz.test;

import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.sql.Time;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class QuizTest  {
    ApplicationContext cntx;
    EntityManagerFactory emf;
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        cntx =new ClassPathXmlApplicationContext("spring/application-config.xml");
        emf = cntx.getBean(EntityManagerFactory.class);
        entityManager = emf.createEntityManager();
    }

    //@Test
    public void quiz_creationTest(){
        Quiz quiz = new Quiz("Java test for beginners");
        entityManager.getTransaction().begin();
        entityManager.persist(quiz);

        quiz = entityManager.find(Quiz.class,1);
        entityManager.getTransaction().commit();
        System.out.println(quiz.toString());
        assertNotNull(quiz);
    }

    @Test
    public void question_creation(){
        quiz_creationTest();
        entityManager.getTransaction().begin();
        Quiz quiz = entityManager.find(Quiz.class, 1);
        Question question = new Question("test question",quiz);
        entityManager.persist(question);
        entityManager.getTransaction().commit();

        question = entityManager.find(Question.class,1);
        System.out.println(question.toString());
        assertNotNull(question);
    }


}