package com.findMeNow.service;

import com.findMeNow.dao.RelationshipDAO;
import com.findMeNow.enums.Status;
import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Relationship;
import com.findMeNow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RelationshipService {
    private final
    Relationship relationship;
    private final
    RelationshipDAO relationshipDAO;


    @Autowired
    public RelationshipService(RelationshipDAO relationshipDAO, Relationship relationship) {
        this.relationshipDAO = relationshipDAO;
        this.relationship = relationship;
    }

    public void addNewRelationship(User userFromId, User userToId) throws InternalServerError, BadRequestException {
        if (userFromId.getId().equals(userToId.getId()))
            throw new BadRequestException("you can not send yourself a request");
        relationship.setUserFrom(userFromId);
        relationship.setUserTo(userToId);
        relationship.setStatus(Status.REQUEST);
        relationshipDAO.save(relationship);

    }

    public void updateRelationship(Relationship relationship) throws InternalServerError {
        relationshipDAO.update(relationship);
    }

    public void deleteRelationship(Relationship relationship) throws InternalServerError {
        if (relationship != null)
            relationshipDAO.delete(Relationship.class, relationship.getId());
    }

    public List<User> findOutcomingRequest(Long userId) throws InternalServerError {
        if (userId == null)
            try {
                throw new BadRequestException("please login");
            } catch (BadRequestException e) {
                e.printStackTrace();
            }
        return relationshipDAO.findOutcomingRequest(userId);
    }

    public List<User> findIncomingRequest(Long userId) throws InternalServerError {
        if (userId == null)
            try {
                throw new BadRequestException("please login");
            } catch (BadRequestException e) {
                e.printStackTrace();
            }
        return relationshipDAO.findIncomingRequest(userId);
    }

//    public Relationship checkRelationship(String userFromId, String userToId) throws InternalServerError {
//        return relationshipDAO
//                .checkRelationsipUserToOtherUser(Long.parseLong(userFromId), Long.parseLong(userToId));
//    }
}
