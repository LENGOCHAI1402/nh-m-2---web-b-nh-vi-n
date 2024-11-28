package com.vti.dto;

import lombok.Data;

@Data
public class LoginInfoDto {

	private int id;
	private String username;
	private String email;
	private String fullName;
	private String role;

}
