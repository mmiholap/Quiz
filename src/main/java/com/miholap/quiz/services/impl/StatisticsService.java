package com.miholap.quiz.services.impl;

import com.miholap.quiz.persistence.dao.IQuizDao;
import com.miholap.quiz.persistence.dao.IStatisticsDao;
import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.persistence.entities.Statistics;
import com.miholap.quiz.services.IQuizService;
import com.miholap.quiz.services.IStatisticsService;
import com.miholap.quiz.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService extends AbstractService<Statistics> implements IStatisticsService {


    @Autowired
    private IStatisticsDao statisticsDao;

    public StatisticsService() {
        super();
    }

    @Override
    protected IOperations<Statistics> getDao() {
        return statisticsDao;
    }
}
