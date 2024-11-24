package com.vti;

import com.vti.Dto.MedicalRecordDto;
import com.vti.Entity.MedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public MedicalRecordDto toDto(MedicalRecord medicalRecord){
		MedicalRecordDto medicalRecordDto = modelMapper().map(medicalRecord, MedicalRecordDto.class);
		medicalRecordDto.setDoctorName(medicalRecord.getDoctor().getUser().getFullName());
		medicalRecordDto.setPatientName(medicalRecord.getPatient().getUser().getFullName());
		return medicalRecordDto;
	}

}
