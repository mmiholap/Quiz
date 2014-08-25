package com.miholap.quiz.persistence.dao.impl;

import com.miholap.quiz.persistence.dao.IQuizDao;
import com.miholap.quiz.persistence.dao.common.AbstractDao;
import com.miholap.quiz.persistence.entities.Quiz;
import org.springframework.stereotype.Repository;


@Repository
public class QuizDao extends AbstractDao<Quiz> implements IQuizDao {
    public QuizDao() {
        super();
        setClazz(Quiz.class);
    }
}
