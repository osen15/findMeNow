package com.findMeNow.controller;

import com.findMeNow.exception.BadRequestException;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.User;
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
            return new ResponseEntity<>("bad request" +
                    " already exists", HttpStatus.BAD_REQUEST);
        } catch (InternalServerError e) {
            return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpSession session, @ModelAttribute User user) {
        System.out.println(user.toString());
        try {
            if (session.getAttribute("user") == null) {
                User resultUser = userService.login(user.getEmail(), user.getPassword());
                session.setAttribute("user", resultUser);
                return new ResponseEntity<>("user/" + resultUser.getId(), HttpStatus.OK);
            } else
                return new ResponseEntity<>("user already logged", HttpStatus.LOCKED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        } catch (InternalServerError e) {
            return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public ResponseEntity<String> logout(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        System.out.println(sessionUser.toString());
        try {
//            if (!sessionUser.getPassword().equals(password))
//                return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
            userService.setLastActive(sessionUser);
        } catch (InternalServerError error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        session.removeAttribute("user");
        return new ResponseEntity<>("index", HttpStatus.OK);
    }
}