package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gl.entity.Role;
import com.gl.entity.User;
import com.gl.service.RoleService;
import com.gl.service.UserService;

@RestController
public class UserRolesController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService rolesService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/roles/list")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView listRoles(Model model) {
		model.addAttribute("roles", rolesService.getAllRoles());
		ModelAndView modelAndView = new ModelAndView("roles");
		return modelAndView;
	}

	@GetMapping("/users/list")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView listUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		ModelAndView modelAndView = new ModelAndView("users");
		return modelAndView;
	}

	@GetMapping("/users/new")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView createNewUser(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		ModelAndView modelAndView = new ModelAndView("create_user");

		modelAndView.addObject("user", user);

		List<Role> roles = (List<Role>) rolesService.getAllRoles();

		modelAndView.addObject("allRoles", roles);

		return modelAndView;
	}

	@GetMapping("/roles/new")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView createNewRole(Model model) {

		Role role = new Role();
		model.addAttribute("role", role);
		ModelAndView modelAndView = new ModelAndView("create_role");
		return modelAndView;
	}

	@PostMapping("/users/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView saveUser(@ModelAttribute("user") User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.saveUsers(user);
		ModelAndView modelAndView = new ModelAndView("redirect:/users/list");
		return modelAndView;
	}

	@PostMapping("/roles/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView saveEmployee(@ModelAttribute("role") Role role) {
		rolesService.saveRoles(role);
		ModelAndView modelAndView = new ModelAndView("redirect:/roles/list");
		return modelAndView;
	}

	@GetMapping("/users/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView editUser(@PathVariable int id, Model model) {
		User user = userService.getUserById(id);

		model.addAttribute("user", user);
		ModelAndView modelAndView = new ModelAndView("edit_user");

		modelAndView.addObject("user", user);

		List<Role> roles = (List<Role>) rolesService.getAllRoles();

		modelAndView.addObject("allRoles", roles);

		return modelAndView;
	}

	@PostMapping("/users/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView updateUser(@PathVariable int id, @ModelAttribute("user") User user, Model model) {

		User existingUser = userService.getUserById(id);
		existingUser.setId(id);
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(user.getPassword());
		existingUser.setRoles(user.getRoles());

		userService.updateUser(existingUser);
		ModelAndView modelAndView = new ModelAndView("redirect:/users/list");
		return modelAndView;
	}

	@GetMapping("/roles/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView editRole(@PathVariable int id, Model model) {
		model.addAttribute("role", rolesService.getRoleById(id));
		ModelAndView modelAndView = new ModelAndView("edit_role");
		return modelAndView;
	}

	@PostMapping("/roles/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView updateRole(@PathVariable int id, @ModelAttribute("role") Role role, Model model) {

		Role existingRole = rolesService.getRoleById(id);
		existingRole.setId(id);
		existingRole.setName(role.getName());

		rolesService.updateRole(existingRole);
		ModelAndView modelAndView = new ModelAndView("redirect:/roles/list");
		return modelAndView;
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/users/list");
		return modelAndView;
	}

	@GetMapping("/roles/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView deleteRole(@PathVariable int id) {
		rolesService.deleteRoleById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/roles/list");
		return modelAndView;
	}
}
