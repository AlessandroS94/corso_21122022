package com.diemme.wrapperForm;

import com.diemme.domain.mysql.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormWrapperChat {

	private String nameProject;
	private User user;
	private String message;

}
