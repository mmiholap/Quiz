package com.miholap.quiz.services.common;

import com.miholap.quiz.persistence.dao.common.IOperations;

import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {
    @Override
    public T findById(int id) {
        return getDao().findById(id);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public void create(T entity) {
        getDao().create(entity);
    }

    @Override
    public T update(T entity) {
        return getDao().update(entity);
    }

    @Override
    public void delete(T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        getDao().deleteById(entityId);
    }

    protected abstract IOperations<T> getDao();

}
