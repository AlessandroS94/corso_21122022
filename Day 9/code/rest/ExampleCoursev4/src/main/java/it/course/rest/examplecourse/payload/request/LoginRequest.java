package it.course.rest.examplecourse.payload.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	@Setter
	@Getter
	private String username;

	@NotBlank
	@Getter
	@Setter

	private String password;
}
