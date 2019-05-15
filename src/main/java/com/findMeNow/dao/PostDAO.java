package com.findMeNow.dao;

import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAO extends GeneralDao<Post> {
    @Override
    public void delete(long id) throws InternalServerError {

    }

    @Override
    Class<Post> getModelClass() {
        return Post.class;
    }
}
