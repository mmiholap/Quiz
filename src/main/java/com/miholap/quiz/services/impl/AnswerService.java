package com.miholap.quiz.services.impl;


import com.miholap.quiz.persistence.dao.IAnswerDao;
import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.services.IAnswerService;
import com.miholap.quiz.services.IQuestionService;
import com.miholap.quiz.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService extends AbstractService<Answer> implements IAnswerService {

    @Autowired
    private IAnswerDao answerDao;

    @Autowired
    private IQuestionService questionService;

    public AnswerService() {
        super();
    }

    @Override
    protected IOperations<Answer> getDao() {
        return answerDao;
    }

    @Override
    public List<Answer> getActiveAnswers(Question _question) {
        Question question = questionService.findById(_question.getId());
        if(question == null){
            return  null;
        }
        return answerDao.selectActiveAnswersForQuestion(question.getId());
    }
}
