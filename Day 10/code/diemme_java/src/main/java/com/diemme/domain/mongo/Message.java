package com.diemme.domain.mongo;

import java.time.LocalDateTime;

import javax.persistence.Version;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "message")
public class Message {

	@Id
	private String id;
	@Version
	private long version;
	@Field("message")
	private String message;
	@Field("file")
	private byte[] file;
	@Field("date")
	private LocalDateTime date;
	@Field("id_user")
	private Long idUser;
	@Field("name_user")
	private String name;

}
