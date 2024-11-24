package com.vti.Service;

import com.vti.Dto.MedicalRecordDto;
import com.vti.Entity.MedicalRecord;
import com.vti.Form.CreateRecordForm;
import com.vti.Repository.DoctorRepository;
import com.vti.Repository.PatientRepository;
import com.vti.Repository.MedicalRecordRepository;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    public final DoctorRepository doctorRepository;
    public final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public MedicalRecordServiceImpl( MedicalRecordRepository medicalRecordRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicalRecordDto createRecord(CreateRecordForm form) {
        var doctor = doctorRepository.findById(form.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + form.getDoctorId()));
        var patient = patientRepository.findById(form.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + form.getPatientId()));
        var medicalRecord = modelMapper.map(form, MedicalRecord.class);
        var savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        return modelMapper.map(savedMedicalRecord, MedicalRecordDto.class);
    }
}

