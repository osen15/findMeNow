package com.findMeNow.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public  class GeneralDAO<T> {


    @PersistenceContext
    private EntityManager entityManager;

    public T get(Class<T> tClass, Long id) {
        return entityManager.find(tClass, id);
    }

    public void save(T t) {
        entityManager.persist(t);
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public void delete(Class<T> tClass, long id) {
        entityManager.remove(get(tClass, id));
    }


}