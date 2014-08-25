package com.miholap.quiz.persistence.dao.impl;

import com.miholap.quiz.persistence.dao.IUserDao;
import com.miholap.quiz.persistence.dao.common.AbstractDao;
import com.miholap.quiz.persistence.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {
    public UserDao() {
        super();
        setClazz(User.class);
    }
}
