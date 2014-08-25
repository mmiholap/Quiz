package com.miholap.quiz.persistence.dao.impl;

import com.miholap.quiz.persistence.dao.IStatisticsDao;
import com.miholap.quiz.persistence.dao.common.AbstractDao;
import com.miholap.quiz.persistence.entities.Statistics;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticsDao extends AbstractDao<Statistics> implements IStatisticsDao {
    public StatisticsDao() {
        super();
        setClazz(Statistics.class);
    }
}
