package com.example.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class TodoItem {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     @NotBlank(message = "Title may not be blank")
     private String title;

}
