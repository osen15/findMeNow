package com.findMeNow.controller;

import com.findMeNow.dao.RelationshipDAO;
import com.findMeNow.dao.UserDAO;
import com.findMeNow.enums.RelationshipStatus;
import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Relationship;
import com.findMeNow.models.User;
import com.findMeNow.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class RelationshipController {
    private final
    RelationshipService relationshipService;
    private final
    UserDAO userDAO;
    private final
    RelationshipDAO relationshipDAO;

    @Autowired
    public RelationshipController(RelationshipService relationshipService, UserDAO userDAO, RelationshipDAO relationshipDAO) {
        this.relationshipService = relationshipService;
        this.userDAO = userDAO;
        this.relationshipDAO = relationshipDAO;
    }


    @RequestMapping(path = "/add-relationship", method = RequestMethod.POST)
    public ResponseEntity<String> requestSave(HttpSession session,
                                              @RequestParam String userToId) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null)
            return new ResponseEntity<>("Please login", HttpStatus.FORBIDDEN);
        try {
            relationshipService.addNewRelationship(sessionUser.getId(), Long.parseLong(userToId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InternalServerError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/update-relationship", method = RequestMethod.POST)
    public ResponseEntity<String> updateRelationship(HttpSession session,
                                                     @RequestParam("userId") String userId,
                                                     @RequestParam("status") String status) throws BadRequestException {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return new ResponseEntity<>("Please login", HttpStatus.FORBIDDEN);
        }
        try {
            Relationship relationship = relationshipDAO.checkStatus(sessionUser.getId(), Long.parseLong(userId));
            relationship.setRelationshipStatus(Enum.valueOf(RelationshipStatus.class, status));
            //relationshipService.updateRelationship(sessionUser.getId().toString(), userId, status);
            relationshipService.updateRelationship(relationship);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InternalServerError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}








