package com.vti.service;

import com.vti.dto.PatientDto;
import com.vti.dto.PatientWithUser;
import com.vti.entity.Patient;
import com.vti.entity.User;
import com.vti.form.patient.CreatePatientForm;
import com.vti.form.patient.UpdatePatientForm;
import com.vti.repository.PatientRepository;
import com.vti.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
// tao them thong tin cho patient
    @Override
    public PatientDto createPatient(Long userId, CreatePatientForm form) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Patient patient = modelMapper.map(form, Patient.class);
        patient.setUser(user);
        Patient savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient, PatientDto.class);
    }
    @Override
    public PatientDto updatePatient(Long userId, UpdatePatientForm form) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Patient patient = modelMapper.map(form, Patient.class);
        patient.setUser(user);
     Patient savedPatient = patientRepository.save(patient);
     return modelMapper.map(savedPatient,PatientDto.class);
    }

    @Override
    public Page<PatientDto> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(patient -> modelMapper.map(patient,PatientDto.class));
    }


    @Override
    public Page<PatientDto> findByFullName(String fullName, Pageable pageable) {
        return patientRepository.findByFullName(fullName,pageable)
                .map(patient -> modelMapper.map(patient,PatientDto.class));
    }


    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

//    lay tat ca thong tin cua bệnh nhân thong qua use id
    @Override
    public PatientWithUser findPatientWithUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Patient patient = patientRepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("User not found"));
        PatientWithUser patientWithUser = modelMapper.map(patient,PatientWithUser.class);
        modelMapper.map(patient,patientWithUser);
        if (patient.getUser() != null) {
            modelMapper.map(patient.getUser(),patientWithUser);
        }
        return patientWithUser;
    }
// lấy ra dánh sách chứa các thông tin cảu bệnh nhân

    @Override
    public List<PatientWithUser> findAllPatientWithUsers() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patient -> {
                    PatientWithUser patientWithUser = modelMapper.map(patient,PatientWithUser.class);
                    modelMapper.map(patient,patientWithUser);
                    if (patient.getUser() != null) {
                        modelMapper.map(patient.getUser(),patientWithUser);
                    }
                    return patientWithUser;
                })
                .collect(Collectors.toList());
    }
    @Override
    public PatientDto findById(Long patientId) {
        return patientRepository.findById(patientId).map(patient -> modelMapper.map(patient,PatientDto.class)).orElse(null);
    }
}
