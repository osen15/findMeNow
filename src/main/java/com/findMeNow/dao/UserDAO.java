package com.findMeNow.dao;

import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO extends GeneralDao<User> {


    @Override
    public void save(User user) throws InternalServerError, BadRequestException {
        super.save(user);
    }
}
