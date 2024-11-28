package com.vti.service;

import com.vti.dto.DoctorDto;
import com.vti.dto.DoctorWithUser;
import com.vti.form.doctor.CreateDoctorForm;
import com.vti.form.doctor.DoctorFitterForm;
import com.vti.form.doctor.UpdateDoctorForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DoctorService {
    Page<DoctorDto> findAll(DoctorFitterForm form,Pageable pageable);
    DoctorDto findById(Long userId);
    Page<DoctorDto> findByFullName(String fullName, Pageable pageable);
    DoctorDto createDoctor(Long userId, CreateDoctorForm createDoctorForm);
    DoctorDto updateDoctor(Long doctorId, UpdateDoctorForm form);
    DoctorWithUser findDoctorWithUser(Long userId);
    List<DoctorWithUser> findAllDoctorsWithUser();
    void deleteDoctor(Long doctorId);
}
