package com.findMeNow.service;


import com.findMeNow.dao.GeneralDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GeneralService<T> extends GeneralDAO<T> {

    public T save(T t) {
        return super.save(t);
    }

    public T update(T t) {
        return super.update(t);
    }

    public void delete(Long id, Class<T> tClass) {
        super.delete(id, tClass);

    }

    public T findById(Long id, Class<T> tClass) {
        return super.findById(id, tClass);
    }
}
