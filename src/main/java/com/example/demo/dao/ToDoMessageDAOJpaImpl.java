package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.ToDoMessage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ToDoMessageDAOJpaImpl implements ToDoMessageDAO{

	private EntityManager entityManager;
	
	public ToDoMessageDAOJpaImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	@Override
	public List<ToDoMessage> findAll() {
		
		TypedQuery<ToDoMessage> query = this.entityManager.createQuery("FROM ToDoMessage", ToDoMessage.class);
		List<ToDoMessage> toDoList = query.getResultList();
		return toDoList;
	}

	@Transactional
	@Override
	public ToDoMessage save(ToDoMessage toDoMessage) {
		
		ToDoMessage toDoMessageSave = entityManager.merge(toDoMessage);
		return toDoMessageSave;
	}

}
