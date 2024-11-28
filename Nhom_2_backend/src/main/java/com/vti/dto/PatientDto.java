package com.vti.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class PatientDto  {
    private String medicalHistory;
    private String allergies;
    private String bloodType;
    private int weight;
    private int height;
}
