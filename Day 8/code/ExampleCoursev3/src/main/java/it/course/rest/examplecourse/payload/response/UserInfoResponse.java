package it.course.rest.examplecourse.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserInfoResponse {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private List<String> roles;

	public UserInfoResponse(Long id, String username, String email, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
