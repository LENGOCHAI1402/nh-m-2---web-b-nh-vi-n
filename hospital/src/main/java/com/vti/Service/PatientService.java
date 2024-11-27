package com.vti.Service;

import com.vti.Dto.PatientDto;
import com.vti.Dto.PatientWithUser;
import com.vti.Form.CreatePatientForm;
import com.vti.Form.UpdatePatientForm;
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
