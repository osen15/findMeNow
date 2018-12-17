package com.findMeNow.service;

import com.findMeNow.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends GeneralService<User> {
}
