package com.miholap.quiz.persistence.dao.impl;

import com.miholap.quiz.persistence.dao.IQuestionDao;
import com.miholap.quiz.persistence.dao.common.AbstractDao;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class QuestionDao extends AbstractDao<Question> implements IQuestionDao {
    public QuestionDao() {
        super();
        setClazz(Question.class);
    }

    @Override
    public List<Question> findActiveQuestionsForQuiz(int quiz_id) {
        TypedQuery<Question> query = entityManager.createNamedQuery("Question.activeQuestions",Question.class);
        query.setParameter("quiz_id",quiz_id);
        return query.getResultList();
    }
}
