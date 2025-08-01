package com.example.todolist.service;

import com.example.todolist.dto.TodoListRequest;
import com.example.todolist.dto.TodoListResponse;
import com.example.todolist.entity.TodoList;
import com.example.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private  final TodoListRepository todoListRepository;

    @Transactional
    public TodoListResponse save(TodoListRequest request){
        TodoList todolist = new TodoList(request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword());
        TodoList savedTodoList = todoListRepository.save(todolist);

        return new TodoListResponse(
                savedTodoList.getId(),
                savedTodoList.getTitle(),
                savedTodoList.getContent(),
                savedTodoList.getAuthor(),
                savedTodoList.getCreatedAt(),
                savedTodoList.getModifiedAt()
                );
    }
}
