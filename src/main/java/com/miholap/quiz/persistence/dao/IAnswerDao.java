package com.miholap.quiz.persistence.dao;

import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Answer;

import java.util.List;

public interface IAnswerDao extends IOperations<Answer> {
    List<Answer> selectActiveAnswersForQuestion(int questionId);
}
