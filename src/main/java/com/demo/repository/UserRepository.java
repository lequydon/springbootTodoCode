package com.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	 User findByEmail(String email);
}
