package com.vti.Service;

import com.vti.Dto.UserDto;
import com.vti.Form.CreateDoctorForm;
import com.vti.Form.CreatePatientForm;
import com.vti.Form.CreateUserForm;
import com.vti.Form.UpdateUserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    Page<UserDto> fillAll(Pageable pageable);

    UserDto findById(Long userId);
    UserDto create(CreateUserForm form);

    UserDto update(Long userId, UpdateUserForm form);

    void delete(Long userId);


}
