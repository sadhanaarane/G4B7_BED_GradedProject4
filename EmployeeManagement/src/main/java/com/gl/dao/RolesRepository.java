package com.gl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

}
