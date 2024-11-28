package com.vti.service;

import com.vti.form.user.CreatingUserFormForAdmin;
import com.vti.form.user.UpdatingUserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.User;
import com.vti.form.user.UserFilterForm;
import com.vti.form.user.CreatingUserForm;

public interface UserService extends UserDetailsService{

	public Page<User> getAllUsers(Pageable pageable, String search, UserFilterForm filterForm);

	public User getUserByID(Long id);
	
	public void createUser(CreatingUserForm form);
	
	public boolean isUserExistsByUsername(String username);
	
	public User getUserByUsername(String username);

	public void deleteUserById(Long userId);
	public void updateUser(Long userId,UpdatingUserForm form);
	public void createUserForAdmin(CreatingUserFormForAdmin form);

}
