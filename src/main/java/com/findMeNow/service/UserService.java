package com.findMeNow.service;

import com.findMeNow.dao.UserDAO;
import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findById(Long id) {
        return userDAO.get(User.class, id);
    }

    public void save(User newUser) throws InternalServerError, BadRequestException {
        userDAO.save(newUser);
    }


}