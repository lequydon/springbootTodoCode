package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Role;
import com.demo.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer>{
    @Override
    List<Status> findAll();
	Status findBynameStatus(String nameStatus);
}
