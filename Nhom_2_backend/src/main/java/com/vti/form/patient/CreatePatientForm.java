package com.vti.form.patient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreatePatientForm{
    private Long userId;
    private String medicalHistory;
    private String insuranceNumber;
    private String allergies;
    private String bloodType;
    private int weight;
    private int height;
}
