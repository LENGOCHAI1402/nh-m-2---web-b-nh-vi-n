package com.vti.Service;

import com.vti.Dto.DoctorDto;
import com.vti.Entity.Doctor;
import com.vti.Form.CreateDoctorForm;
//import com.vti.Form.UpdateDoctorForm;
import com.vti.Form.DoctorFitterForm;
import com.vti.Form.UpdateDoctorForm;
import com.vti.Repository.DoctorRepository;
import com.vti.Repository.UserRepository;
import com.vti.specification.DoctorSpecialization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<DoctorDto> findAll(DoctorFitterForm form,Pageable pageable) {
        var spec = DoctorSpecialization.buildSpec(form);
        return doctorRepository.findAll(spec,pageable)
                .map(doctor -> modelMapper.map(doctor, DoctorDto.class));
    }

    @Override
    public DoctorDto findById(Long userId) {
        return doctorRepository.findById(userId).map(doctor -> modelMapper.map(doctor, DoctorDto.class)).orElse(null);
    }

    @Override
    public Page<DoctorDto> findByFullName(String fullName, Pageable pageable) {
        return doctorRepository.findByFullName(fullName, pageable)
                .map(doctor -> modelMapper.map(doctor, DoctorDto.class));
    }



    @Override
    public DoctorDto createDoctor(Long userId, CreateDoctorForm form) {
        var doctor = modelMapper.map(form, Doctor.class);
        var savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor, DoctorDto.class);
    }



    @Override
    public DoctorDto updateDoctor(Long userId, UpdateDoctorForm form) {
        var doctor = doctorRepository.findById(userId).orElse(null);
        modelMapper.map(form, doctor);
        var savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor, DoctorDto.class);
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        userRepository.deleteById(doctorId);
    }
}
