package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gl.entity.Employee;
import com.gl.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/list")
	@PreAuthorize("permitAll()")
	public ModelAndView listEmployees(Model model) {

		model.addAttribute("employees", employeeService.getAllEmployees());
		ModelAndView modelAndView = new ModelAndView("employees");
		return modelAndView;
	}

	@GetMapping("/view/{id}")
	@PreAuthorize("permitAll()")
	public ModelAndView viewStudent(@PathVariable int id, Model model) {

		model.addAttribute("employee", employeeService.getEmployeeById(id));
		ModelAndView modelAndView = new ModelAndView("view_employee");
		return modelAndView;

	}

	@GetMapping("/getEmployeesSortedByFirstName/{dir}")
	@PreAuthorize("permitAll()")
	public ModelAndView getEmployeesSortedByFirstName(@PathVariable Direction dir, Model model) {

		model.addAttribute("employees", employeeService.getEmployeesSortedByFirstName(dir));
		ModelAndView modelAndView = new ModelAndView("employees");
		return modelAndView;
	}

	@GetMapping("/new")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView createEmployeeForm(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		ModelAndView modelAndView = new ModelAndView("create_employee");
		return modelAndView;
	}

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee) {

		employeeService.saveEmployee(employee);
		ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");
		return modelAndView;
	}

	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView editemployeeForm(@PathVariable int id, Model model) {

		model.addAttribute("employee", employeeService.getEmployeeById(id));
		ModelAndView modelAndView = new ModelAndView("edit_employee");
		return modelAndView;
	}

	@PostMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView updateemployee(@PathVariable int id, @ModelAttribute("employee") Employee employee,
			Model model) {

		// get employee from database by id
		Employee existingEmployee = employeeService.getEmployeeById(id);
		existingEmployee.setId(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		// save updated employee object
		employeeService.updateEmployee(existingEmployee);
		ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");
		return modelAndView;
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView deleteemployee(@PathVariable int id) {
		employeeService.deleteEmployeeById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");
		return modelAndView;
	}

	@GetMapping("/search")
	@PreAuthorize("permitAll()")
	public ModelAndView searchEmployee(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, Model model) {

		ModelAndView modelAndView;

		if (firstName.trim().isEmpty() && lastName.trim().isEmpty())
			return new ModelAndView("redirect:/employees/list");
		else {
			List<Employee> employees = employeeService.searchBy(firstName, lastName);

			model.addAttribute("employees", employees);

			modelAndView = new ModelAndView("employees");

			return modelAndView;

		}
	}
}
