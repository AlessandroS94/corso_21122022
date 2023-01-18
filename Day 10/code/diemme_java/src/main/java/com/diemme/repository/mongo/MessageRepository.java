package com.diemme.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diemme.domain.mongo.Message;

public interface MessageRepository extends MongoRepository <Message, String>{

}
