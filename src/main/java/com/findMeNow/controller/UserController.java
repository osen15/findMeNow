package com.findMeNow.controller;

import com.findMeNow.dao.UserDAO;
import com.findMeNow.models.User;
import com.findMeNow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ServerErrorException;

@Controller
public class UserController {
    private UserService userService;
    private UserDAO userDAO;

    @Autowired
    public UserController(UserService userService, UserDAO userDAO) {
        this.userService = userService;
        this.userDAO = userDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}")
    public String profile(Model model, @PathVariable String userId) {
        try {
            User user = userService.get(User.class, Long.valueOf(userId));
            System.out.println(user.getId());
            model.addAttribute("user", user);
            return "profile";
        } catch (NumberFormatException | NullPointerException e) {
            model.addAttribute("error", e);
            return "errors/badRequestError";
        } catch (ServerErrorException e) {
            model.addAttribute("error", e);
            return "errors/systemError";
        }
    }
}
