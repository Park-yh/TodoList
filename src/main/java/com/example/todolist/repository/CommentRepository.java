package com.example.todolist.repository;

import com.example.todolist.entity.Comment;
import com.example.todolist.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByTodoList(TodoList todoList);
}
