package com.miholap.quiz.persistence.dao.impl;

import com.miholap.quiz.persistence.dao.ITagDao;
import com.miholap.quiz.persistence.dao.common.AbstractDao;
import com.miholap.quiz.persistence.entities.Tag;

public class TagDao extends AbstractDao<Tag> implements ITagDao {
    public TagDao() {
        super();
        setClazz(Tag.class);
    }
}
