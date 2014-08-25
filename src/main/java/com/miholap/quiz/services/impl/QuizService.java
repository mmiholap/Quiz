package com.miholap.quiz.services.impl;

import com.miholap.quiz.persistence.dao.IQuizDao;
import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.services.IQuizService;
import com.miholap.quiz.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService extends AbstractService<Quiz> implements IQuizService {


    @Autowired
    private IQuizDao quizDao;

    public QuizService() {
        super();
    }

    @Override
    protected IOperations<Quiz> getDao() {
        return quizDao;
    }
}
