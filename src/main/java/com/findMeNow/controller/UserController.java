package com.findMeNow.controller;

import com.findMeNow.exception.InternalServerError;
import com.findMeNow.exception.NotFoundException;
import com.findMeNow.models.User;
import com.findMeNow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
        } catch (NumberFormatException e) {
            model.addAttribute("error", e);
            return "errors/badRequestError";
        } catch (InternalServerError e) {
            model.addAttribute("error", e);
            return "errors/systemError";
        }
    }
}
