package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

}
