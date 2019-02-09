package com.findMeNow.dao;

import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GeneralDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public T get(Class<T> tClass, Long id) throws InternalServerError {
        try {
            return entityManager.find(tClass, id);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public T save(T t) throws InternalServerError, BadRequestException {
        try {
            entityManager.persist(t);

        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
        return t;
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public void delete(Class<T> tClass, long id) throws InternalServerError {
        try {
            entityManager.remove(get(tClass, id));
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


}
