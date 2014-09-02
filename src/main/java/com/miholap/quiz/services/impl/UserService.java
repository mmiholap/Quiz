package com.miholap.quiz.services.impl;

import com.miholap.quiz.persistence.dao.IUserDao;
import com.miholap.quiz.persistence.dao.common.IOperations;
import com.miholap.quiz.persistence.entities.User;
import com.miholap.quiz.services.IUserService;
import com.miholap.quiz.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> implements IUserService {
    @Autowired
    IUserDao userDao;

    public UserService(){
        super();
    }

    @Override
    protected IOperations<User> getDao() {
        return userDao;
    }
}
