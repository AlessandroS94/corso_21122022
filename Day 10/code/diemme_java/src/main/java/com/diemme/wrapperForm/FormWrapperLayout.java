package com.diemme.wrapperForm;

import java.util.Set;

import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class FormWrapperLayout {
	
	private Layout layout;
	private Set<User> userClient;

}
