package com.vti.form.user;

import com.vti.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UpdatingUserForm {
    private String fullname;
    private String email;
    private String address;
    private String phoneNumber;
    private User.Gender gender;
    private Date birthday;
    private String password;
}
