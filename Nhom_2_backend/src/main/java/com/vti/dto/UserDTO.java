package com.vti.dto;

import com.vti.entity.User;
import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

	private int id;
	private String username;
	private String fullname;
	private String email;
	private String address;
	private String phoneNumber;
	private String gender;
	private String role;
	private Date createAt;

}
