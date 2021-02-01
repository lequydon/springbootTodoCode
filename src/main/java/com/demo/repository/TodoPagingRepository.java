package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.entity.Todo;

public interface TodoPagingRepository extends PagingAndSortingRepository<Todo,Integer> {
	
}
