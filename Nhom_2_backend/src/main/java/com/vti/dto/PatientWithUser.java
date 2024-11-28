package com.vti.dto;


import com.vti.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientWithUser {
    private Long userId;
    private String userName;
    private String email;
    private String fullName;
    private User.Role role;
    private String phone;
    private String gender;
    private String address;
    private LocalDate birthday;
    private String medicalHistory;
    private String allergies;
    private String bloodType;
    private int weight;
    private int height;
}
