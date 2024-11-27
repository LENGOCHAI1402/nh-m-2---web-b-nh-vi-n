package com.vti.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientFilterForm {
    private Long userId;
    private String medicalHistory;
    private String insuranceNumber;
    private String allergies;
    private String bloodType;
    private Integer minWeight;
    private Integer maxWeight;
    private Integer minHeight;
    private Integer maxHeight;
}
