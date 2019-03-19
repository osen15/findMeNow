package com.findMeNow.controller;

import com.findMeNow.dao.RelationshipDAO;
import com.findMeNow.enums.TypeOfButtons;
import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Relationship;
import com.findMeNow.models.User;
import com.findMeNow.service.RelationshipService;
import com.findMeNow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    private final UserService userService;
    private final RelationshipDAO relationshipDAO;
    private final RelationshipService relationshipService;

    @Autowired
    public UserController(UserService userService, RelationshipDAO relationshipDAO, RelationshipService relationshipService) {
        this.userService = userService;
        this.relationshipDAO = relationshipDAO;
        this.relationshipService = relationshipService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    public String profile(Model model, HttpSession session, @PathVariable("id") Long id) {
        try {
            User userSession = (User) session.getAttribute("user");
            User userFindById = userService.findById(id);
            model.addAttribute("user", userFindById);
            model.addAttribute("userSession", userSession);

            if (!userSession.getId().equals(id)) {
                Relationship relationship = relationshipDAO.checkStatus(userSession.getId(), id);
                model.addAttribute("relationship", relationship.getStatus().toString());
            }

            if (userSession.getId().equals(id)) {
                model.addAttribute("outcomingRequests", relationshipService.findOutcomingRequest(id));
                model.addAttribute("incomingRequests", relationshipService.findIncomingRequest(id));
            }
            return "profile";
        } catch (BadRequestException e) {
            model.addAttribute("error", e);
            return "errors/badRequestError";
        } catch (InternalServerError e) {
            model.addAttribute("error", e);
            return "errors/systemError";
        } catch (NullPointerException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> login(HttpSession session,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password) {
        try {
            if (session.getAttribute("user") == null) {
                User resultUser = userService
                        .login(email, password);
                session.setAttribute("user", resultUser);
                return new ResponseEntity<>("user/" + resultUser.getId(), HttpStatus.OK);
            } else
                return new ResponseEntity<>("user already logged", HttpStatus.FORBIDDEN);
        } catch (BadRequestException e) {
            return new ResponseEntity<>("wrong password or email", HttpStatus.BAD_REQUEST);
        } catch (InternalServerError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpSession session, @RequestParam("password") String password) {
        User sessionUser = (User) session.getAttribute("user");
        System.out.println(sessionUser.toString());
        try {
            if (!sessionUser.getPassword().equals(password))
                return new ResponseEntity<>("wrong password", HttpStatus.BAD_REQUEST);
            userService.setLastActive(sessionUser);
        } catch (InternalServerError error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        session.removeAttribute("user");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}