package com.vti.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdatePatientForm extends CreateUserForm{
    private String medicalHistory;
    private String insuranceNumber;
    private String allergies;
    private String bloodType;
    private int weight;
    private int height;
}
