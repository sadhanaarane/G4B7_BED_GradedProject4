package com.gl.service;

import java.util.List;

import com.gl.entity.Role;

public interface RoleService {

	public List<Role> getAllRoles();

	public void saveRoles(Role role);

	public Role getRoleById(int id);

	public Role updateRole(Role role);

	public void deleteRoleById(int id);
}