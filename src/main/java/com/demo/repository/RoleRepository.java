package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}
