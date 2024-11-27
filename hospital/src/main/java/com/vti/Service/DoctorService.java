package com.vti.Service;

import com.vti.Dto.DoctorDto;
import com.vti.Dto.DoctorWithUser;
import com.vti.Form.CreateDoctorForm;
//import com.vti.Form.UpdateDoctorForm;
import com.vti.Form.DoctorFitterForm;
import com.vti.Form.UpdateDoctorForm;
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
