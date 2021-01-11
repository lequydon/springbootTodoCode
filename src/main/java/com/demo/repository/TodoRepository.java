package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Todo;
import com.demo.entity.User;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
 List<Todo> findAllByUser(User user);
 List<Todo> findByUserOrderByContentAsc(User user);
 List<Todo> findByUserOrderByContentDesc(User user);
}
