package com.example.todolist.controller;

import com.example.todolist.dto.TodoListRequest;
import com.example.todolist.dto.TodoListResponse;
import com.example.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoListController {
    private final TodoListService todoListService;

    @PostMapping("/todolists")
    public ResponseEntity<TodoListResponse> createTodoList(
            @RequestBody TodoListRequest request
            ) {
        return ResponseEntity.ok(todoListService.save(request));
    }
}
