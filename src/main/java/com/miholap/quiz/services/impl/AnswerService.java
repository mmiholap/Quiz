package com.miholap.quiz.services.impl;


import com.miholap.quiz.persistence.dao.IAnswerDao;
import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.services.IAnswerService;
import com.miholap.quiz.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService extends AbstractService<Answer> implements IAnswerService {

    @Autowired
    private IAnswerDao answerDao;

    public AnswerService() {
        super();
    }

    @Override
    protected IOperations<Answer> getDao() {
        return answerDao;
    }
}
