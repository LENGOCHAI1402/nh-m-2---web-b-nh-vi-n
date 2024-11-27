package com.vti.Controller;

import com.vti.Dto.PatientDto;
import com.vti.Dto.PatientWithUser;
import com.vti.Form.CreatePatientForm;
import com.vti.Form.UpdatePatientForm;
import com.vti.Service.PatientService;
import com.vti.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PatientController {
    private PatientService patientService;
    private final UserService userService;
    @Autowired
    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

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

//    in ra toan bo thong tin cua benh nhan
    @GetMapping("/api/v1/patient/{userId}")
    public PatientWithUser findPatientWithUser(@PathVariable("userId") Long userId) {
        return patientService.findPatientWithUser(userId);
    }

    //tao thong tin benh nhan dua tren user id
    @PostMapping("api/v1/user/{userId}/patients")
    public PatientDto createPatient(@PathVariable("userId") Long userId, @RequestBody CreatePatientForm form) {
        return patientService.createPatient(userId,form);
    }
    @PutMapping("api/v1/user/{userId}/patients")
    public PatientDto update(@PathVariable("userId") Long userId, @RequestBody UpdatePatientForm form) {
        return patientService.updatePatient(userId,form);
    }

//    in ra list benh nhan day du thong tin
    @GetMapping("api/patients")
    public ResponseEntity<List<PatientWithUser>> getAllPatientsWithUser() {
        List<PatientWithUser> patientWithUsers = patientService.findAllPatientWithUsers();
        return ResponseEntity.ok(patientWithUsers);
    }

















//    ------------ không dùng đến----------------
// sua thong tin benh nhan
//    xoa thong tin benh nhan
    @DeleteMapping("api/v1/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("id") Long patientId) {
        patientService.deletePatient(patientId);
    }

}
