package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Service.Interface.ITodoService;
import com.demo.entity.Todo;
import com.demo.entity.User;
import com.demo.repository.TodoRepository;
import com.demo.repository.UserRepository;
@Service
public class TodoService implements ITodoService {
	@Autowired
	private TodoRepository todoResRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<Todo> getListTodo(String email) {
		// TODO Auto-generated method stub
		User user= userRepository.findByEmail(email);
		List<Todo> listTodo =todoResRepository.findAllByUser(user);
		return listTodo;
	}

}
