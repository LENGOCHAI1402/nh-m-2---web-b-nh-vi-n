package com.vti.Dto;

import com.vti.Entity.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDto {
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
