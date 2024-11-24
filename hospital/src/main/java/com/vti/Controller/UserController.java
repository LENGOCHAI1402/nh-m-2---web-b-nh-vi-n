package com.vti.Controller;

import com.vti.Dto.UserDto;
import com.vti.Form.CreateDoctorForm;
import com.vti.Form.CreatePatientForm;
import com.vti.Form.CreateUserForm;
import com.vti.Form.UpdateUserForm;
import com.vti.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

//   lấy toàn bộ dữ liệu người dùng
    @GetMapping("api/v1/users")
    public Page<UserDto> findAll(Pageable pageable){
    return userService.fillAll(pageable);
    }

//    tìm kiếm thông tin người dùng theo id
    @GetMapping("api/v1/users/{userId}")
    public UserDto findById(@PathVariable("userId") Long id) {
        return userService.findById(id);
    }

//    tạo người dùng
    @PostMapping("/api/v1/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody CreateUserForm form) {
        return userService.create(form) ;

    }

// cập nhật thông tin người dùng theo id
    @PutMapping("api/v1/users/{id}")
    public UserDto update(@PathVariable("id") Long id,@RequestBody UpdateUserForm form) {
        return userService.update(id, form);
    }
// xóa người  dùng theo id
    @DeleteMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
