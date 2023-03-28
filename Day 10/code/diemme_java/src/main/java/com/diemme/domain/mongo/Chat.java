package com.diemme.domain.mongo;

import java.util.Set;

import jakarta.persistence.Enumerated;
import jakarta.persistence.Version;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chat")
public class Chat {

	@Id
	private String id;
	@Version
	private long version;
	@Field("chat_type")
	@Enumerated
	private ChatType chatType;
	@DBRef
	@Field("messages")
	private Set<Message> messages;

}
