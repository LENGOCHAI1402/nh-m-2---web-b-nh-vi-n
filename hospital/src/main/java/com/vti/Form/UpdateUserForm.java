package com.vti.Form;

import com.vti.Entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UpdateUserForm {
    private String userName;
    private String email;
    private String password;
    private String fullName;
    private UserRole role;
    private String phone;
    private String gender;
    private String address;
    private LocalDate birthday;
}
