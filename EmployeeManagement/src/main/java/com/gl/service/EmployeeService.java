package com.gl.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.gl.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public void saveEmployee(Employee theEmployee);

	public Employee getEmployeeById(int id);

	public Employee updateEmployee(Employee theEmployee);

	public void deleteEmployeeById(int empId);

	public List<Employee> searchBy(String firstName, String lastName);

	public List<Employee> getEmployeesSortedByFirstName(Direction direction);
}
