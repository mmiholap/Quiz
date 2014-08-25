package com.miholap.quiz.services;

import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;


import java.util.List;


public interface IQuestionService extends IOperations<Question> {
    List<Question> getNRandomQuestions(Quiz quiz, int N);
}
