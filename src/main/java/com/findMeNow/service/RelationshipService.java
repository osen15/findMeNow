package com.findMeNow.service;

import com.findMeNow.dao.RelationshipDAO;
import com.findMeNow.dao.UserDAO;
import com.findMeNow.enums.RelationshipStatus;
import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Relationship;
import com.findMeNow.models.User;
import com.findMeNow.validation.RelationshipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RelationshipService {
    private Relationship relationship;
    private RelationshipDAO relationshipDAO;
    private UserDAO userDAO;
    private RelationshipValidation relationshipValidation;


    @Autowired
    public RelationshipService(RelationshipDAO relationshipDAO, Relationship relationship, UserDAO userDAO, RelationshipValidation relationshipValidation) {
        this.relationshipDAO = relationshipDAO;
        this.relationship = relationship;
        this.userDAO = userDAO;
        this.relationshipValidation = relationshipValidation;
    }

    public void addNewRelationship(Long userFromId, Long userToId) throws InternalServerError, BadRequestException {
        User userFrom = userDAO.get(userFromId);
        User userTo = userDAO.get(userToId);
        relationship = relationshipDAO.checkStatus(userFromId, userToId);
        relationshipValidation.validationForSaveRelationship(relationship);
        relationship.setUserFrom(userFrom);
        relationship.setUserTo(userTo);
        relationship.setRelationshipStatus(RelationshipStatus.REQUEST);
        relationshipDAO.save(relationship);
    }

    public void updateRelationship(Relationship relationship) throws InternalServerError, BadRequestException {
        relationshipValidation.validationForUpdateRelationship(relationship);
        relationshipDAO.update(relationship);
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

}
