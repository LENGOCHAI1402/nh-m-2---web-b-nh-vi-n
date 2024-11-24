package com.vti.Controller;

import com.vti.Dto.PatientDto;
import com.vti.Form.CreatePatientForm;
import com.vti.Form.UpdatePatientForm;
import com.vti.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class PatientController {
    private PatientService patientService;
//tim toan bo thong tin benh nhan
    @GetMapping("api/v1/patients")
    public Page<PatientDto> findAll(Pageable pageable) {
        return patientService.findAll(pageable);
    }
// tim benh nhan theo id
    @GetMapping("api/v1/patients/{id}")
    public PatientDto findById(@PathVariable("id") Long id) {
        return patientService.findById(id);
    }

//tao thong tin benh nhan
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping("api/v1/user/{userId}/patient")
    public PatientDto createPatient(@PathVariable("userId") Long userId, @RequestBody CreatePatientForm form) {
        return patientService.createPatient(userId,form);
    }
// sua thong tin benh nhan
    @PutMapping("api/v1/patients/{id}")
    public PatientDto update(@PathVariable("id") Long id, @RequestBody UpdatePatientForm form) {
        return patientService.updatePatient(id,form);
    }
//    xoa thong tin benh nhan
    @DeleteMapping("api/v1/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("id") Long patientId) {
        patientService.deletePatient(patientId);
    }

}
