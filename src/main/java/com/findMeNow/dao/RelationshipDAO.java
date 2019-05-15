package com.findMeNow.dao;


import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Relationship;

import com.findMeNow.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class RelationshipDAO extends GeneralDao<Relationship> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String getRelationshipStatus =
            "SELECT * FROM RELATIONSHIPS WHERE USER_FROM_ID = :userFromId AND USER_TO_ID = :userToId " +
                    "OR USER_TO_ID = :userFromId AND USER_FROM_ID = :userToId";

    private static final String updateRelationship =
            "UPDATE RELATIONSHIPS SET STATUS = :status WHERE USER_FROM_ID = :userFromId AND USER_TO_ID =:userToId";

    private static final String outgoingRequest = "SELECT DISTINCT USERS. * FROM USERS JOIN RELATIONSHIPS ON USERS.ID = USER_TO_ID" +
            " WHERE USER_FROM_ID = :userId AND STATUS = 'REQUEST'";

    private static final String incomingRequests = "SELECT DISTINCT USERS.* FROM USERS JOIN RELATIONSHIPS ON  USERS.ID = USER_FROM_ID" +
            " WHERE USER_TO_ID = :userId AND STATUS = 'REQUEST'";

    @Override
    public Relationship save(Relationship relationship) throws InternalServerError, BadRequestException {
        return super.save(relationship);
    }

    @Override
    public void delete(long id) throws InternalServerError {
        Relationship relationship = get(id);
        if (relationship != null)
            super.delete(relationship.getId());
    }

//    public void updateRelationship(Long userFromId, Long userToId, String status) throws InternalServerError {
//        try {
//            entityManager.createNativeQuery(updateRelationship, Relationship.class)
//                    .setParameter("userFromId", userFromId)
//                    .setParameter("userToId", userToId)
//                    .setParameter("status", status)
//                    .executeUpdate();
//        } catch (Exception e) {
//            throw new InternalServerError(e.getMessage());
//        }
//    }


    @Override
    public void update(Relationship relationship) throws InternalServerError {
        if (get(relationship.getId()) != null)
            super.update(relationship);
    }

    @Override
    Class<Relationship> getModelClass() {
        return Relationship.class;
    }

    public Relationship checkStatus(Long userFromId, Long userToId) {
        return (Relationship) entityManager.createNativeQuery(getRelationshipStatus, Relationship.class)
                .setParameter("userFromId", userFromId)
                .setParameter("userToId", userToId).getSingleResult();
    }

    @Override
    public Relationship get(Long id) throws InternalServerError {
        return super.get(id);
    }

    public List<User> findOutcomingRequest(Long userId) throws InternalServerError {
        List<User> result;
        try {
            result = (List<User>) entityManager.createNativeQuery(outgoingRequest, User.class)
                    .setParameter("userId", userId);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
        return result;

    }

    public List<User> findIncomingRequest(Long userId) throws InternalServerError {
        List<User> result;
        try {
            result = (List<User>) entityManager.createNativeQuery(incomingRequests, User.class)
                    .setParameter("userId", userId);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
        return result;

    }

}
