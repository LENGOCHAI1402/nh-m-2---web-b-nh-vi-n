package com.vti.form.doctor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorForm {
    private Long userId;
    private String specialization;
    private int experienceYears;
    private String education;
    private String certifications;
}
