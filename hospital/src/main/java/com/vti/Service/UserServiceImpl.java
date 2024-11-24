package com.vti.Service;

import com.vti.Dto.UserDto;
import com.vti.Entity.User;
import com.vti.Form.CreateUserForm;
import com.vti.Form.UpdateUserForm;
import com.vti.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service

public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private  final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
// tim thong tin nguoi dung
    @Override
    public Page<UserDto> fillAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> modelMapper.map(user, UserDto.class));

    }
//tim nguoi dung theo id
@Override
public UserDto findById(Long userId) {
    return userRepository.findById(userId)
            .map(user -> modelMapper.map(user, UserDto.class))
            .orElseThrow(() -> new EntityNotFoundException("User không tồn tại với id: " + userId));
}

    // tạo thông tin người dùng
    @Override
    public UserDto create(CreateUserForm form) {
        var user = modelMapper.map(form, User.class);
        var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

// cập nhật thông tin người dùng theo id
@Override
public UserDto update(Long userId, UpdateUserForm form) {
    var user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    modelMapper.map(form, user);
    var savedUser = userRepository.save(user);
    return modelMapper.map(savedUser, UserDto.class);
}


    //  xoa thông tin người dùng
    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }



}
