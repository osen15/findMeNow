package com.findMeNow.service;


import com.findMeNow.models.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService extends GeneralService<Message> {
}
