package com.vti.service;

import com.vti.form.user.CreatingUserFormForAdmin;
import com.vti.form.user.UpdatingUserForm;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vti.entity.User;
import com.vti.form.user.UserFilterForm;
import com.vti.form.user.CreatingUserForm;
import com.vti.repository.UserRepository;
import com.vti.specification.user.UserSpecification;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Page<User> getAllUsers(
			Pageable pageable, 
			String search, 
			UserFilterForm filterForm) {
		
		Specification<User> where = UserSpecification.buildWhere(search, filterForm);
		return repository.findAll(where, pageable);
	}
	
	public User getUserByID(Long id) {
		return repository.findById(id).get();
	}

	public void createUser(CreatingUserForm form) {
		TypeMap<CreatingUserForm, User> typeMap = modelMapper.getTypeMap(CreatingUserForm.class, User.class);
		if (typeMap == null) {
			modelMapper.addMappings(new PropertyMap<CreatingUserForm, User>() {
				@Override
				protected void configure() {
					skip(destination.getUserId());
				}
			});
		}
		User user = modelMapper.map(form, User.class);
		if (user.getRole() == null) {
			user.setRole(User.Role.Patient);
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repository.save(user);
	}
	public boolean isUserExistsByUsername(String username) {
		return repository.existsByUsername(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}
	
	@Override
	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public void deleteUserById(Long userId) {
		repository.deleteById(userId);
	}

	@Override
	public void updateUser(Long userId, UpdatingUserForm form) {
		User existingUser = repository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		existingUser.setFullname(form.getFullname());
		existingUser.setEmail(form.getEmail());
		existingUser.setAddress(form.getAddress());
		existingUser.setPhoneNumber(form.getPhoneNumber());
		existingUser.setGender(form.getGender());
		existingUser.setBirthday(form.getBirthday());

		if (form.getPassword() != null && !form.getPassword().isEmpty()) {
			existingUser.setPassword(passwordEncoder.encode(form.getPassword()));
		}
		repository.save(existingUser);
	}

	@Override
	public void createUserForAdmin(CreatingUserFormForAdmin form) {
			User user = new User();
			user.setUsername(form.getUsername());
			user.setEmail(form.getEmail());
			user.setFullname(form.getFullname());
			user.setPassword(passwordEncoder.encode(form.getPassword()));
			user.setRole(form.getRole());
			user.setAddress(form.getAddress());
			user.setPhoneNumber(form.getPhoneNumber());
			user.setGender(form.getGender());
			user.setBirthday(form.getBirthday());
			user.setCreateAt(new java.util.Date());

			repository.save(user);
		}
}


