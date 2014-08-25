package com.miholap.quiz.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

//import com.google.common.base.Preconditions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings("unchecked")
public abstract class AbstractDao<T extends Serializable> implements IOperations<T> {
    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;
    // API

    protected final void setClazz(final Class<T> clazzToSet) {
        //clazz = Preconditions.checkNotNull(clazzToSet);
        clazz = clazzToSet;
    }

    @Override
    public final T findById(final int id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public final List<T> findAll() {
        //System.out.println("DEBUG: findall"+entityManager.toString());
        return entityManager.createQuery("SELECT a FROM " + clazz.getSimpleName()+" a", clazz).getResultList();
    }

    @Override
    public final void create(final T entity) {
        //System.out.println("DEBUG: create"+entityManager.toString());
        //Preconditions.checkNotNull(entity);
        entityManager.persist(entity);
    }

    @Override
    public final T update(final T entity) {
        //Preconditions.checkNotNull(entity);
        return  entityManager.merge(entity);
    }

    @Override
    public final void delete(final T entity) {
        //Preconditions.checkNotNull(entity);
        //getCurrentSession().delete(entity);
        entityManager.remove(entity);
    }

    @Override
    public final void deleteById(final int entityId) {
        final T entity = findById(entityId);
        //Preconditions.checkState(entity != null);
        delete(entity);
    }

}
