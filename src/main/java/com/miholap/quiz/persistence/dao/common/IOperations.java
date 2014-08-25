package com.miholap.quiz.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

    T findById(final int id);

    List<T> findAll();

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final int entityId);

}
