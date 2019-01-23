package com.findMeNow.controller;


import com.findMeNow.models.Post;
import com.findMeNow.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get/{id}")
    // public @ResponseBody
    public String doGet(Model model, @PathVariable String id) {
        model.addAttribute("post", postService.findById(Long.parseLong(id)));
        return "home";
    }

}
