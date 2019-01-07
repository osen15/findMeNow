package com.findMeNow.service;

import com.findMeNow.models.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService extends GeneralService<Post> {

}
