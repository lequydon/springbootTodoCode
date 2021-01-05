package com.demo.Service.Interface;

import java.util.List;

import com.demo.dto.TodoDTO;
import com.demo.entity.Todo;

public interface ITodoService {
List<Todo> getListTodo(String email);
Todo getTodo(int id);
Todo updateTodo(TodoDTO todoDTO);
Todo addTodo(TodoDTO todoDTO);
}
