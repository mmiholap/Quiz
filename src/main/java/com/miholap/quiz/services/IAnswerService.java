package com.miholap.quiz.services;

import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;

import java.util.List;

public interface IAnswerService extends IOperations<Answer>{
    List<Answer> getActiveAnswers(Question question);
}
