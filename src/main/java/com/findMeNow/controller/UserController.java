package com.findMeNow.controller;

import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;
import com.findMeNow.service.UserService;
import jdk.internal.instrumentation.Logger;
import oracle.jdbc.logging.annotations.Logging;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    public String profile(Model model, @PathVariable("id") Long id) {
        try {
            User user = userService.findById(id);
            if (user == null) return "errors/notFoundException";
            model.addAttribute("user", user);
            return "profile";
        } catch (BadRequestException e) {
            model.addAttribute("error", e);
            return "errors/badRequestError";
        } catch (InternalServerError e) {
            model.addAttribute("error", e);
            return "errors/systemError";
        }
    }

    @RequestMapping(path = "/register-user", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@ModelAttribute User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>("user registered!", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>("bad request or user with phone/email" +
                    " already exists", HttpStatus.BAD_REQUEST);
        } catch (InternalServerError e) {
            return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
