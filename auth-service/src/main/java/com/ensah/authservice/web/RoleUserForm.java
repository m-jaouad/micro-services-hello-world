package com.ensah.authservice.web;

import lombok.Data;

@Data // for getters and setters
class RoleUserForm {
	private String username;
	private String role;
}
