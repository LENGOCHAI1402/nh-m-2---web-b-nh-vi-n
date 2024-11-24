package com.vti.Service;

import com.vti.Dto.PatientDto;
import com.vti.Entity.Patient;
import com.vti.Form.CreatePatientForm;
import com.vti.Form.UpdatePatientForm;
import com.vti.Repository.PatientRepository;
import com.vti.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public PatientDto createPatient(Long userId, CreatePatientForm form) {
        var user = userRepository.findById(userId).orElse(null);
        var patient = modelMapper.map(form, Patient.class);
        patient.setUser(user);
        var savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient, PatientDto.class);
    }

    @Override
    public Page<PatientDto> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(patient -> modelMapper.map(patient,PatientDto.class));
    }

    @Override
    public PatientDto findById(Long patientId) {
        return patientRepository.findById(patientId).map(patient -> modelMapper.map(patient,PatientDto.class)).orElse(null);
    }

    @Override
    public Page<PatientDto> findByFullName(String fullName, Pageable pageable) {
        return patientRepository.findByFullName(fullName,pageable)
                .map(patient -> modelMapper.map(patient,PatientDto.class));
    }

    @Override
    public PatientDto updatePatient(Long userId, UpdatePatientForm updatePatientForm) {
        var patient = patientRepository.findById(userId).orElse(null);
        modelMapper.map(updatePatientForm, patient);
        var savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient, PatientDto.class);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }
}
