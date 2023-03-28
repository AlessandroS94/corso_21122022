package com.diemme.domain.mysql;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "chat_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser extends BaseModel {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@Column(name = "name_project", nullable = false)
	@NotBlank
	private String nameProject;
	@Column(name = "id_chat_mongo", nullable = false)
	private String idChatMongo;

}
