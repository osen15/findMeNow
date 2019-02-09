package com.findMeNow.dao;


import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO extends GeneralDao<User> {
    @Override
    public User get(Class<User> userClass, Long id) throws InternalServerError {
        return super.get(userClass, id);
    }

    @Override
    public User save(User user) throws InternalServerError, BadRequestException {
        try {
            super.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("user with phone or mail already exists", e.getMessage());
        }
        return user;
    }
}

