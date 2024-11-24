package com.vti.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {
    private String medicalHistory;
    private String allergies;
    private String bloodType;
    private int weight;
    private int height;
}
