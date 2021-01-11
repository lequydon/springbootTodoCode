package com.demo.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Service.Interface.ITodoService;
import com.demo.dto.TodoDTO;
import com.demo.entity.Status;
import com.demo.entity.Todo;
import com.demo.entity.User;
import com.demo.repository.StatusRepository;
import com.demo.repository.TodoRepository;
import com.demo.repository.UserRepository;
import com.demo.Service.UserService;
@Service
public class TodoService implements ITodoService {
	@Autowired
	private TodoRepository todoResRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Override
	public List<Todo> getListTodo(String email,String sort) {
		// TODO Auto-generated method stub
		User user= userRepository.findByEmail(email);
		List<Todo> listTodo=new ArrayList<Todo>();
		if(sort.equals("")) {
			listTodo =todoResRepository.findAllByUser(user);	
		}
		if(sort.equals("asc")) {
			listTodo =todoResRepository.findByUserOrderByContentAsc(user);	
		}
		if(sort.equals("desc")) {
			listTodo =todoResRepository.findByUserOrderByContentDesc(user);	
		}
		List<Todo> listTodoDelete =new ArrayList<Todo>();
		for(Todo i:listTodo) {
			if(i.getDeleted()==0) {
				listTodoDelete.add(i);
			}
		}
		System.out.print(listTodoDelete);
		return listTodoDelete;
	}
	@Override
	public Todo getTodo(int id) {
		// TODO Auto-generated method stub
		Todo todo=todoResRepository.findOne(id);
		return todo;
	}
	@Override
	public Todo updateTodo(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
    	Todo todo =new Todo();
    	todo.setId(todoDTO.getId());
		todo.setContent(todoDTO.getContent());
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			todo.setDealine(formatter.parse(todoDTO.getDeadlineString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Status status=statusRepository.findOne(todoDTO.getStatusId());
		todo.setStatus(status);
		todo.setDeleted(0);
		User user=userRepository.findByEmail(todoDTO.getEmail());
		todo.setUser(user);
		todoResRepository.save(todo);
		return todo;
	}
	@Override
	public Todo addTodo(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
    	Todo todo =new Todo();
		todo.setContent(todoDTO.getContent());
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			todo.setDealine(formatter.parse(todoDTO.getDeadlineString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Status status=statusRepository.findOne(todoDTO.getStatusId());
		todo.setStatus(status);
		todo.setDeleted(0);
		User user=userRepository.findByEmail(todoDTO.getEmail());
		todo.setUser(user);
		todoResRepository.save(todo);
		return todo;
	}
	@Override
	public Todo deleteTodo(int id) {
		// TODO Auto-generated method stub
		Todo todo=todoResRepository.findOne(id);
		todo.setDeleted(1);
		todoResRepository.save(todo);
		return todo;
	}

}
