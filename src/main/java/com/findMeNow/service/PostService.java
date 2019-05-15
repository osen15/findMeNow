package com.findMeNow.service;


import com.findMeNow.dao.PostDAO;
import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {
    private final PostDAO postDAO;


    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public Post findById(Long id) throws InternalServerError {
        return postDAO.get(id);
    }
}
