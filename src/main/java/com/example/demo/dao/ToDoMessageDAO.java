package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.ToDoMessage;

public interface ToDoMessageDAO {

	List<ToDoMessage> findAll();
	
	ToDoMessage save(ToDoMessage toDoMessage);
}
