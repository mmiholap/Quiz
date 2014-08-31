package com.miholap.quiz.componentBeans;

import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.persistence.entities.Statistics;
import com.miholap.quiz.services.IAnswerService;
import com.miholap.quiz.services.IQuestionService;
import com.miholap.quiz.services.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@Scope("session")
public class QuizMB implements Iterable<Question>{
    @Autowired
    private IQuizService quizService;
    @Autowired
    private IQuestionService questionService;

    private Quiz quiz;
    private Statistics statistics;
    private List<Question> questions;
    private Iterator<Question> iterator;
    private int quizSize;

    public void initManager(int qzId, int nQuestions){
        setQuiz(quizService.findById(qzId));
        setQuizSize(nQuestions);
        questions = questionService.getNRandomQuestions(quiz,nQuestions);
        iterator = questions.iterator();
    }

    public Question next(){
        return iterator.next();
    }

    public boolean hasNext(){
        return iterator.hasNext();
    }
    @Override
    public Iterator<Question> iterator() {
        return iterator;
    }

    public int getQuizSize() {
        return quizSize;
    }

    public void setQuizSize(int quizSize) {
        this.quizSize = quizSize;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public IQuizService getQuizService() {
        return quizService;
    }

    public void setQuizService(IQuizService quizService) {
        this.quizService = quizService;
    }

    public IQuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(IQuestionService questionService) {
        this.questionService = questionService;
    }

}
