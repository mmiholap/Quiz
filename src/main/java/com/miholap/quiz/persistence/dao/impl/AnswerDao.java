package com.miholap.quiz.persistence.dao.impl;

import com.miholap.quiz.persistence.dao.IAnswerDao;
import com.miholap.quiz.persistence.dao.common.AbstractDao;
import com.miholap.quiz.persistence.entities.Answer;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDao extends AbstractDao<Answer> implements IAnswerDao {
    public AnswerDao() {
        super();
        setClazz(Answer.class);
    }
}
