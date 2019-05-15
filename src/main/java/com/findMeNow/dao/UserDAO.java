package com.findMeNow.dao;


import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;


@Repository
public class UserDAO extends GeneralDao<User> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String searchUsersByPhoneAndEmail = "SELECT * FROM USERS WHERE PHONE = ? OR EMAIL = ?";
    private static final String searchUserByEmail = "SELECT * FROM USERS WHERE EMAIL = ?";

    @Override
    public User get(Long id) throws InternalServerError {
        return super.get(id);
    }

    @Override
    public User save(User user) throws InternalServerError, BadRequestException {
        if (!searchUsersByPhoneAndEmail(user).isEmpty())
            throw new BadRequestException("user with phone or email already exists");
        return super.save(user);
    }

    @Override
    public void update(User user) throws InternalServerError {
        super.update(user);
    }

    @Override
    public void delete(long id) throws InternalServerError {

    }

    @Override
    Class<User> getModelClass() {
        return User.class;
    }

    public User findByEmail(String email) {
        return (User) entityManager.createNativeQuery(searchUserByEmail, User.class)
                .setParameter(1, email).getSingleResult();
    }

    public ArrayList<User> searchUsersByPhoneAndEmail(User user) {
        ArrayList<User> result = (ArrayList<User>) entityManager.createNativeQuery(searchUsersByPhoneAndEmail, User.class)
                .setParameter(1, user.getPhone())
                .setParameter(2, user.getEmail())
                .getResultList();
        return result;
    }


}


