package com.diemme.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diemme.domain.mongo.Chat;

public interface ChatRepository extends MongoRepository<Chat, String> {

	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query(value = "UPDATE FROM Chat c WHERE c.id = :idChatMongo") public void
	 * updateChatMongoDb(@Param("idChatMongo") String idChatMongo);
	 */

}
