package com.diemme.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.diemme.domain.mongo.Chat;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {

}
