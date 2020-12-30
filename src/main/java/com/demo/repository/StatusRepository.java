package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Role;
import com.demo.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer>{
	Status findBynameStatus(String nameStatus);
}
