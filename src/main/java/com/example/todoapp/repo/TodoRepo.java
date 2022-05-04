package com.example.todoapp.repo;

import com.example.todoapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TodoRepo extends JpaRepository<TodoItem, Long> {

}
