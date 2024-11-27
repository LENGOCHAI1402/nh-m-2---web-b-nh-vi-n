package com.vti.Dto;

import com.vti.Entity.UserRole;
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
    private UserRole role;
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
