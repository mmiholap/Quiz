package com.miholap.quiz.persistence.dao;

import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;

import java.util.List;

public interface IQuestionDao extends IOperations<Question> {
    List<Question> findActiveQuestionsForQuiz(int quiz_id);
}
