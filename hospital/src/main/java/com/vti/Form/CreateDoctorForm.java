package com.vti.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorForm extends CreateUserForm{
    private String specialization;
    private int experienceYears;
    private String education;
    private String certifications;
}
