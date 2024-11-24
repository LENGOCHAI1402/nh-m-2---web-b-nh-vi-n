package com.vti.Form;

import com.vti.Entity.BloodType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CreateRecordForm {
    private Long recordId;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private String diagnosis;
    private String treatment;
    private String prescription;
    private String notes;
    private BloodType blood;
    private LocalDateTime createdAt;
}
