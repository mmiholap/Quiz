package com.miholap.quiz.componentBeans;

import com.miholap.quiz.persistence.entities.*;
import com.miholap.quiz.services.*;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("session")
public class QuizMB implements Iterable<Question>{
    @Autowired
    private IQuizService quizService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private IStatisticsService statisticsService;
    @Autowired
    private IUserService userService;

    private Quiz quiz;
    private Statistics statistics;
    private List<Question> questions;
    private Iterator<Question> iterator;
    private int quizSize;
    private int rightAnswers;
    private Question currentQuestion;
    private Map<Integer,List<Answer>> answersMap ;

    public void initManager(int qzId, int nQuestions){
        setQuiz(quizService.findById(qzId));
        questions = questionService.getNRandomQuestions(quiz,nQuestions);
        quizSize = questions.size();
        iterator = questions.iterator();
        answersMap = new HashMap<>(20);
        for(Question question : questions){
            answersMap.put(question.getId(),answerService.getActiveAnswers(question));
        }
        //statistics = new Statistics();

    }

    public void resetManager(){
        quiz = null;
        statistics = null;
        questions = null;
        iterator = null;
        quizSize = 0;
        rightAnswers = 0;
        currentQuestion = null;
        answersMap = null;
    }

    public void calculateStatistics(){
        User user = userService.findById(1);//hardcoded to anonymous user
        statistics = new Statistics(quiz,user,rightAnswers,quizSize, Calendar.getInstance());
        statisticsService.create(statistics);
    }

    public List<Answer> getAnswersForCurrentQuestion(){
        return answersMap.get(currentQuestion.getId());
    }

    public void addAnswerToStatistics(int answerId){
        if(isRightAnswer(answerId)){
            rightAnswers++;
        }
    }

    public boolean isRightAnswer(int answerId){
        if (answerId <= 0){
            return false;
        }
        Answer answer = answerService.findById(answerId);
        if(answer == null){
            return false;
        }
        return answer.isRight();
    }

    public Question next(){
        Question q = iterator.next();
        currentQuestion = q;
        return q;
    }

    public boolean hasNext(){
        return iterator.hasNext();
    }
    @Override
    public Iterator<Question> iterator() {
        return iterator;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IAnswerService getAnswerService() {
        return answerService;
    }

    public void setAnswerService(IAnswerService answerService) {
        this.answerService = answerService;
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
