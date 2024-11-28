package com.vti.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class DoctorDto {
    private long doctorId;
    private String specialization;
    private int experienceYears;
    private String education;
    private String certifications;
}
