package com.divy.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class UserCreateForm {

	@NotEmpty
	private String email = "";
	
	@NotEmpty
	private String password = "";
	
	@NotEmpty
	private String passwordRepeated = "";
	
	@NotEmpty
	private Role role = Role.USER;
}
