package com.miholap.quiz.test;

import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/application-config.xml" )
public class QuizTest  {
    @Autowired
    EntityManagerFactory emf;

    EntityManager entityManager;

    DataInitialization init;

    private final String[] queriesForClearDB = {
            "DELETE FROM Answer",
            "DELETE FROM Question",
            "DELETE FROM Quiz"
    };

    private void clearDataBase(EntityManager entityManager){
        entityManager.getTransaction().begin();
        for(String query : queriesForClearDB) {
            entityManager.createQuery(query).executeUpdate();
        }
        entityManager.getTransaction().commit();
    }

    @Before
    public void before() throws Exception {
        init = new DataInitialization(emf);
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void after(){
        entityManager.getTransaction().commit();
        clearDataBase(entityManager);
    }

    @Test
    public void testConfigurationLoader(){

    }


    @Test
    public void quiz_creationTest(){
        final String query = "SELECT qz FROM Quiz qz";
        init.createQuizs();
        TypedQuery<Quiz> q =  entityManager.createQuery(query,Quiz.class);
        List<Quiz> quizs = q.getResultList();
        assertTrue(quizs.size() == DataInitialization.QUIZ_SIZE);
    }

    @Test
    public void question_creation(){
        final String query = "SELECT qz FROM Quiz qz";
        init.createQuestions();
        TypedQuery<Quiz> q =  entityManager.createQuery(query,Quiz.class);
        List<Quiz> quizs = q.getResultList();
        for(Quiz quiz : quizs){
            Collection<Question> questions = quiz.getQuestions();
            for(Question question: questions){
                System.out.println("DEBUG: "+question.toString());
            }
            assertTrue(questions.size() > 0);
        }
    }

    @Test
    public void answer_creation(){
        final String query = "SELECT COUNT(answer.id) FROM Answer answer";
        long expectCount = init.createAnswers();
        long count =(long) entityManager.createQuery(query).getSingleResult();
        assertTrue(expectCount == count);
    }


}