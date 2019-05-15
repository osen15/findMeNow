package com.findMeNow.dao;

import com.findMeNow.exception.InternalServerError;
import com.findMeNow.models.Message;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO extends GeneralDao<Message> {


    @Override
    public void delete(long id) throws InternalServerError {

    }

    @Override
    Class<Message> getModelClass() {
        return Message.class;
    }
}
