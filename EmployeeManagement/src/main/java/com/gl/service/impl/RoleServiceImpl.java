package com.gl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dao.RolesRepository;
import com.gl.entity.Role;
import com.gl.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return rolesRepository.findAll();
	}

	@Override
	public void saveRoles(Role role) {
		// TODO Auto-generated method stub
		rolesRepository.save(role);
	}

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return rolesRepository.findById(id).get();
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return rolesRepository.save(role);
	}

	@Override
	public void deleteRoleById(int id) {
		// TODO Auto-generated method stub
		rolesRepository.deleteById(id);
	}

}
