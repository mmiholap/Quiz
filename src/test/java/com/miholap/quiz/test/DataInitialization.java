package com.miholap.quiz.test;

import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.persistence.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataInitialization {

    private EntityManagerFactory entityManagerFactory;

    public static  final int QUIZ_SIZE = 10;
    public static final int QUESTION_SIZE = 5;
    public static final int ANSWER_SIZE = 3;
    public static final int TAG_SIZE = 3;

    public DataInitialization(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void createQuizs(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for(Quiz quiz : generateQuizes()){
            entityManager.persist(quiz);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void createQuestions(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for(Question question: generateQuestions()){
            entityManager.persist(question.getQuiz());
            entityManager.persist(question);
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public int createAnswers(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Answer> answers = generateAnswers();
        for(Answer answer: answers){
            entityManager.persist(answer.getQuestion().getQuiz());
            entityManager.persist(answer.getQuestion());
            entityManager.persist(answer);
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return answers.size();
    }

    public void createTags(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Tag> tags = generateTags();

        for(int i = 0; i < tags.size(); i++){
            entityManager.persist(tags.get(i));
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<Quiz> generateQuizes(){
        List<Quiz> quizs = new ArrayList<>(QUIZ_SIZE);

        for(int i = 0; i < QUIZ_SIZE; i++) {
            quizs.add(new Quiz("description " + i,"description"));
        }

        return quizs;
    }

    public List<Question> generateQuestions(){
        List<Question> questions = new ArrayList<>(QUESTION_SIZE*QUIZ_SIZE);
        Random rand = new Random(47);
        for (int i = 0; i < QUIZ_SIZE;i++){
            Quiz quiz = new Quiz("testQuestion "+ i,"description");
            for (int j = 0; j < QUESTION_SIZE - rand.nextInt(2) ; j++){
                questions.add(new Question(i+" question text "+ j, quiz));
            }
        }

        return questions;
    }

    public List<Answer> generateAnswers(){
        Random rand = new Random(47);
        List<Answer> answers = new ArrayList<>();
        for(Question question : generateQuestions()){
            int delta = -1 + rand.nextInt(2);
            for(int i = 0; i < ANSWER_SIZE + delta; i++){
                answers.add(new Answer(question.getId()+" answer for a question "+i, question,
                                        (i>ANSWER_SIZE-1?false:true) ) );
            }
        }

        return answers;
    }

    public List<Tag> generateTags(){
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < TAG_SIZE; i++){
            tags.add(new Tag("#TAG"+i));
        }
        return tags;
    }

    public List<Quiz> generateQuizWithTags(){
        List<Tag> tags = generateTags();
        List<Quiz> quizs = generateQuizes();

        Iterator<Quiz> iterator = quizs.iterator();
        while(iterator.hasNext()){
            iterator.next().setTags(tags);
        }

        return quizs;
    }
}
