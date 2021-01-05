package com.demo.Service.Interface;

import java.util.List;

import com.demo.entity.Todo;

public interface ITodoService {
List<Todo> getListTodo(String email);
Todo getTodo(int id);
}
