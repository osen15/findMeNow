package com.findMeNow.service;

import com.findMeNow.dao.UserDAO;
import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Service
@Transactional
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findById(Long id) throws BadRequestException, InternalServerError {
        try {
            return userDAO.get(User.class, id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public User save(User newUser) throws BadRequestException, InternalServerError {
        newUser.setDateregistered(new Date());
        userDAO.save(newUser);
        return newUser;
    }

    public User login(String email, String password) throws BadRequestException, InternalServerError {
        User result = userDAO.findByEmail(email);
        if (result == null)
            throw new BadRequestException("user is not registered or invalid email");
        if (!result.getPassword().equals(password))
            throw new BadRequestException("invalid password");
        return result;
    }

    public void setLastActive(User user) throws InternalServerError {
        user.setDateLastActive(new Date());
        userDAO.update(user);
    }


}
