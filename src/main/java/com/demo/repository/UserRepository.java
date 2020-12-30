package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findByEmail(String email);
}
