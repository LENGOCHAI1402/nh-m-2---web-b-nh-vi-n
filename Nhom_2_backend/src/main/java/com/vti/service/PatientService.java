package com.vti.service;

import com.vti.dto.PatientDto;
import com.vti.dto.PatientWithUser;
import com.vti.form.patient.CreatePatientForm;
import com.vti.form.patient.UpdatePatientForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    PatientDto createPatient(Long userId, CreatePatientForm createPatientForm);
    Page<PatientDto> findAll(Pageable pageable);
    PatientDto findById(Long patientId);
    Page<PatientDto> findByFullName(String fullName, Pageable pageable);
    PatientDto updatePatient(Long userId, UpdatePatientForm updatePatientForm);
    void deletePatient(Long patientId);
    PatientWithUser findPatientWithUser(Long userId);
    List<PatientWithUser> findAllPatientWithUsers();
}
