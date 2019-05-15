package com.findMeNow.dao;

import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public abstract class GeneralDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public T get(Long id) throws InternalServerError {
        try {
            return entityManager.find(getModelClass(), id);
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


    public void update(T t) throws InternalServerError {
        try {
            entityManager.merge(t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerError(e.getMessage());
        }
    }

    public void delete(Long id) throws InternalServerError {

        try {

            entityManager.detach(get(id));
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public abstract void delete(long id) throws InternalServerError;

    abstract Class<T> getModelClass();
}
