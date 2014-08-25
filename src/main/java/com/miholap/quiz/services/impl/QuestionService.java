package com.miholap.quiz.services.impl;

import com.miholap.quiz.persistence.dao.IQuestionDao;
import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.services.IQuestionService;
import com.miholap.quiz.services.IQuizService;
import com.miholap.quiz.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService extends AbstractService<Question> implements IQuestionService{
    @Autowired
    private IQuestionDao questionDao;

    @Autowired
    private IQuizService quizService;

    @Override
    protected IOperations<Question> getDao() {
        return questionDao;
    }

    @Override
    public List<Question> getNRandomQuestions(Quiz quiz, int N) {
        quiz = quizService.findById(quiz.getId());

        if(quiz == null){
            return  null;
        }

        List<Question> questions = questionDao.findActiveQuestionsForQuiz(quiz.getId());
        Collections.shuffle(questions);

        if(N > questions.size()){
            N = questions.size();
        }

        List<Question> result = new ArrayList<>(questions.subList(0,N));
        return  result;
    }
}
