package com.vti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import com.vti.form.user.CreatingUserFormForAdmin;
import com.vti.form.user.UpdatingUserForm;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.vti.dto.UserDTO;
import com.vti.entity.User;
import com.vti.form.user.UserFilterForm;
import com.vti.form.user.CreatingUserForm;
import com.vti.service.UserService;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService service;

	@GetMapping()
	public Page<UserDTO> getAllUsers(
			Pageable pageable, 
			@RequestParam(value = "search", required = false) String search,
			UserFilterForm filterForm) {

		Page<User> entityPages = service.getAllUsers(pageable, search, filterForm);

		// convert entities --> dtos
		List<UserDTO> dtos = modelMapper.map(
				entityPages.getContent(), 
				new TypeToken<List<UserDTO>>() {}.getType());

		Page<UserDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

		return dtoPages;
	}
	
	@GetMapping(value = "/{id}")
	public UserDTO getUserByID(@PathVariable(name = "id") Long id) {
		User entity = service.getUserByID(id);

		// convert entity to dto
		UserDTO dto = modelMapper.map(entity, UserDTO.class);

		return dto;
	}

	@PostMapping()
	public void createUser(@RequestBody CreatingUserForm form) {
		service.createUser(form);
	}

	@DeleteMapping(value = "/{user_id}")
	public void deleteById(@PathVariable("user_id") long user_id) {
		service.deleteUserById(user_id);
	}

	@PutMapping("/{user_id}")
	public void update(@PathVariable("user_id") Long user_id, @RequestBody UpdatingUserForm form) {
		service.updateUser(user_id, form);
	}
	@PostMapping("/admin")
	public void createUserForAdmin (@RequestBody CreatingUserFormForAdmin form) {
		service.createUserForAdmin(form);
	}
}

