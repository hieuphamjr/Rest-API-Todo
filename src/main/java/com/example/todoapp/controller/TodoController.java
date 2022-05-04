package com.example.todoapp.controller;

import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @GetMapping("/todos/")
    public List<TodoItem> findAll() {
        return todoRepo.findAll();
    }

    @GetMapping("/todos")
    public List<TodoItem> findByTitle(@RequestParam(required = false) String title) {

        List<TodoItem> todoItems = todoRepo.findAll();

        List<TodoItem> result = new ArrayList<>();
        for (TodoItem todoItem : todoItems) {
            if(todoItem.getTitle().toLowerCase().contains(title.toLowerCase())){
                result.add(todoItem);
            }
        }
        return result;
    }

    @GetMapping("/todos/{id}")
    public Optional<TodoItem> findById(@PathVariable Long id) {
        return todoRepo.findById(id);
    }

    @PostMapping("/todos/")
    public TodoItem create(@RequestBody TodoItem todoItem) {
        TodoItem newTodoItem = new TodoItem(null, todoItem.getTitle());
        return todoRepo.save(newTodoItem);
    }

    @PutMapping("/todos/")
    public ResponseEntity save(@RequestBody TodoItem todoItem) {
        Optional<TodoItem> toDoItemInDB = findById(todoItem.getId());
        if(!toDoItemInDB.equals(null)){
            TodoItem todoItemUpdate = new TodoItem(todoItem.getId(), todoItem.getTitle());
            return ResponseEntity.ok(todoRepo.save(todoItemUpdate));
        }
        else{
            return null;
        }
    }

    @DeleteMapping("/todos/{id}")
    public String deleteTodos(@PathVariable Long id) {
        todoRepo.deleteById(id);
        return "Delete To Do with id: " + id;
    }
}
