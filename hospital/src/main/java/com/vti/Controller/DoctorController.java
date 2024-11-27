package com.vti.Controller;

import com.vti.Dto.DoctorDto;
import com.vti.Dto.DoctorWithUser;
import com.vti.Dto.UserDto;
import com.vti.Form.CreateDoctorForm;
import com.vti.Form.CreateUserForm;
import com.vti.Form.DoctorFitterForm;
import com.vti.Form.UpdateDoctorForm;
import com.vti.Service.DoctorService;
import com.vti.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
public class DoctorController {
    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    public DoctorController(DoctorService doctorService, UserService userService) {
        this.doctorService = doctorService;
        this.userService = userService;
    }
//    tìm danh sách bác sĩ
    @GetMapping("api/v1/doctors")
    public Page<DoctorDto> findAll(DoctorFitterForm form,Pageable pageable) {
        return doctorService.findAll(form,pageable);
    }
//tim bac si theo id
//    @GetMapping("api/v1/doctors/{userId}")
//    public DoctorDto findById(@PathVariable("userId") Long id) {
//        return doctorService.findById(id);
//    }

//    tim kiem bac si theo ten
    @GetMapping("api/v1/doctors/search")
    public Page<DoctorDto> findByFullName(String fullName, Pageable pageable) {
        return doctorService.findByFullName(fullName, pageable);
    }
// in ra toan bo thong tin cua bac si theo userId
    @GetMapping("/api/v1/doctors/{userId}")
    public DoctorWithUser findDoctorWithUserById(@PathVariable("userId") Long userId) {
        return doctorService.findDoctorWithUser(userId);
    }

// tạo bác sĩ thông tin thêm cho bác sĩ theo userId
    @PostMapping("api/v1/users/{userId}/doctors")
    public DoctorDto createDoctor(@PathVariable("userId") Long userId,@RequestBody CreateDoctorForm form) {
        return doctorService.createDoctor(userId, form);
    }
// lay ra danh sach cua bac si. trong ddo co toan bo thong tin o bang user
    @GetMapping("api/doctors")
    public ResponseEntity<List<DoctorWithUser>> getAllDoctorsWithUser() {
        List<DoctorWithUser> doctorWithUsers = doctorService.findAllDoctorsWithUser();
        return ResponseEntity.ok(doctorWithUsers);
    }















//    ーーーーーーーーーーーーーーーーーーーーーkhông dùng đến ------------------
// update thong tin bac si theo id
    @PutMapping("api/v1/doctors/{userId}/doctor")
    public DoctorDto updateDoctor(@PathVariable("userId") Long userId, @RequestBody UpdateDoctorForm form) {
        return doctorService.updateDoctor(userId, form);
    }
//    xoa thong tin bac si
    @DeleteMapping("api/v1/doctors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable("id") Long doctorId) {
        doctorService.deleteDoctor(doctorId);
    }
}
