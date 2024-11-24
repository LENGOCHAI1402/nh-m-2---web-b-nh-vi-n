package com.vti.Controller;

import com.vti.Dto.MedicalRecordDto;
import com.vti.Form.CreateRecordForm;
import com.vti.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }
    public MedicalRecordDto createMedicalRecord(CreateRecordForm form) {
        return medicalRecordService.createRecord(form);
    }

}
