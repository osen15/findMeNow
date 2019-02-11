package com.findMeNow.dao;


import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;


@Repository
public class UserDAO extends GeneralDao<User> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String searchUsersByPhoneAndEmail = "SELECT * FROM USERS WHERE PHONE = ? OR EMAIL = ?";

    @Override
    public User get(Class<User> userClass, Long id) throws InternalServerError {
        return super.get(userClass, id);
    }

    @Override
    public User save(User user) throws InternalServerError, BadRequestException {
        if (!searchUsersByPhoneAndEmail(user).isEmpty())
            throw new BadRequestException("user with phone or email already exists");
        return super.save(user);
    }

    private ArrayList<User> searchUsersByPhoneAndEmail(User user) {
        ArrayList<User> result = (ArrayList<User>) entityManager.createNativeQuery(searchUsersByPhoneAndEmail, User.class)
                .setParameter(1, user.getPhone())
                .setParameter(2, user.getEmail())
                .getResultList();
        return result;
    }


}
