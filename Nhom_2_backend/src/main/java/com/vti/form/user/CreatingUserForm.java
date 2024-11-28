package com.vti.form.user;

import com.vti.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreatingUserForm {
	private String username;
	private String email;
	private String password;
	private String fullname;
	private String address;
	private String phoneNumber;
	private User.Gender gender;
	private Date birthday;
}

