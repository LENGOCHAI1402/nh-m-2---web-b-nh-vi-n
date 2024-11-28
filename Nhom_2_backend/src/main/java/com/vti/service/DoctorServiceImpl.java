package com.vti.service;

import com.vti.dto.DoctorDto;
import com.vti.dto.DoctorWithUser;
import com.vti.entity.Doctor;
import com.vti.entity.User;
import com.vti.form.doctor.CreateDoctorForm;
import com.vti.form.doctor.DoctorFitterForm;
import com.vti.form.doctor.UpdateDoctorForm;
import com.vti.repository.DoctorRepository;
import com.vti.repository.UserRepository;
import com.vti.specification.doctor.DoctorSpecialization;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

// in tat ca doctor
    @Override
    public Page<DoctorDto> findAll(DoctorFitterForm form,Pageable pageable) {
        var spec = DoctorSpecialization.buildSpec(form);
        return doctorRepository.findAll(spec,pageable)
                .map(doctor -> modelMapper.map(doctor, DoctorDto.class));
    }
// tìm doctor theo userId
    @Override
    public DoctorDto findById(Long userId) {
        return doctorRepository.findById(userId).map(doctor -> modelMapper.map(doctor, DoctorDto.class)).orElse(null);
    }

// tim kiem theo name
    @Override
    public Page<DoctorDto> findByFullName(String fullName, Pageable pageable) {
        return doctorRepository.findByFullName(fullName, pageable)
                .map(doctor -> modelMapper.map(doctor, DoctorDto.class));
    }


//tao thong tin doctor theo userId
@Override
public DoctorDto createDoctor(Long userId, CreateDoctorForm form) {
    // Tìm User dựa trên userId
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    Doctor doctor = modelMapper.map(form, Doctor.class);
    doctor.setUser(user);
    Doctor savedDoctor = doctorRepository.save(doctor);
    return modelMapper.map(savedDoctor, DoctorDto.class);
}
////sửa thông tin cho bác sĩ
//    @Override
//    public DoctorDto updateDoctor(Long userId, UpdateDoctorForm form) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Doctor doctor = doctorRepository.findByUser(user).orElseThrow(() -> new RuntimeException("User not found"));
//        modelMapper.map(form, doctor);
//        var savedDoctor = doctorRepository.save(doctor);
//        return modelMapper.map(savedDoctor, DoctorDto.class);
//    }

//    sửa thông tin của doctor
@Override
public DoctorDto updateDoctor(Long userId, UpdateDoctorForm form) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    Doctor doctor = modelMapper.map(form, Doctor.class);
    doctor.setUser(user);
    Doctor savedDoctor = doctorRepository.save(doctor);
    return modelMapper.map(savedDoctor, DoctorDto.class);
}
// lay thong toan bo thong tin cua bac si(o bangr user theo userid)
    @Override
    public DoctorWithUser findDoctorWithUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Doctor doctor = doctorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Doctor not found for User ID: " + userId));
        DoctorWithUser doctorWithUser = modelMapper.map(doctor, DoctorWithUser.class);
        modelMapper.map(doctor, doctorWithUser);
        if (doctor.getUser() != null) {
            modelMapper.map(doctor.getUser(), doctorWithUser);
        }
        return doctorWithUser;
    }
// lay ra danh sach doctor (ca thong tin o bang user)
    @Override
    public List<DoctorWithUser> findAllDoctorsWithUser() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(doctor -> {
                    DoctorWithUser doctorWithUser = modelMapper.map(doctor, DoctorWithUser.class);
                    modelMapper.map(doctor, doctorWithUser);
                    if (doctor.getUser() != null) {
                        modelMapper.map(doctor.getUser(), doctorWithUser);
                    }
                    return doctorWithUser;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void deleteDoctor(Long doctorId) {
        userRepository.deleteById(doctorId);
    }
}
