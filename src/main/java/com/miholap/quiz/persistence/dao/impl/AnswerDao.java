package com.miholap.quiz.persistence.dao.impl;

import com.miholap.quiz.persistence.dao.IAnswerDao;
import com.miholap.quiz.persistence.dao.common.AbstractDao;
import com.miholap.quiz.persistence.entities.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AnswerDao extends AbstractDao<Answer> implements IAnswerDao {
    public AnswerDao() {
        super();
        setClazz(Answer.class);
    }

    @Override
    public List<Answer> selectActiveAnswersForQuestion(int questionId) {
        TypedQuery<Answer> query = entityManager.createNamedQuery("Answer.selectActiveAnswersForQuiz",Answer.class);
        query.setParameter("question_id",questionId);
        List<Answer> answers = query.getResultList();
        return answers;
    }
}
