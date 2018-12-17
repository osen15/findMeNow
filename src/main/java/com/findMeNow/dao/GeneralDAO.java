package com.findMeNow.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GeneralDAO<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    public T save(T t) {
        entityManager.persist(t);
        return t;
    }

    public T update(T t) {
        entityManager.merge(t);
        return t;
    }

    public void delete(Long id, Class<T> tClass) {
        entityManager.remove(entityManager.find(tClass, id));
    }

    public T findById(Long id, Class<T> tClass) {
        return entityManager.find(tClass, id);
    }
}
