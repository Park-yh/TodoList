package com.example.todolist.service;

import com.example.todolist.dto.CommentRequest;
import com.example.todolist.dto.CommentResponse;
import com.example.todolist.entity.Comment;
import com.example.todolist.entity.TodoList;
import com.example.todolist.repository.CommentRepository;
import com.example.todolist.repository.TodoListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {
    private final TodoListRepository todoListRepository;
    private final CommentRepository commentRepository;

    public CommentService(TodoListRepository todoListRepository, CommentRepository commentRepository) {
        this.todoListRepository = todoListRepository;
        this.commentRepository = commentRepository;
    }

    public CommentResponse createComment(Long todolistId, CommentRequest request) {
        TodoList todoList = todoListRepository.findById(todolistId).orElseThrow(
                () -> new IllegalArgumentException("TodoList not found with id: " + todolistId)
        );

        if (commentRepository.countByTodoList(todoList) >= 10) {
            throw new IllegalArgumentException("Comments are full for this TodoList.");
        }

        Comment comment = new Comment(request.getContent(), request.getAuthor(), request.getPassword(), todoList);
        comment = commentRepository.save(comment);

        return new CommentResponse(comment);
    }
}
