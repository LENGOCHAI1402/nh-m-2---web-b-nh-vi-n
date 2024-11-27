package com.vti.Service;

import com.vti.Dto.DoctorDto;
import com.vti.Dto.DoctorWithUser;
import com.vti.Dto.UserDto;
import com.vti.Entity.Doctor;
import com.vti.Entity.User;
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

import java.util.List;
import java.util.Optional;
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
