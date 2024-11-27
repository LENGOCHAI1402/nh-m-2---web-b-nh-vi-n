package com.vti.Dto;

import com.vti.Entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DoctorWithUser {
    private Long userId;
    private String userName;
    private String email;
    private String fullName;
    private UserRole role;
    private String phone;
    private String gender;
    private String address;
    private LocalDate birthday;
    private String specialization;
    private int experienceYears;
    private String education;
    private String certifications;
}
