package com.example.todolist.controller;

import com.example.todolist.dto.TodoListRequest;
import com.example.todolist.dto.TodoListResponse;
import com.example.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/todolists")
    public ResponseEntity<List<TodoListResponse>> getTodolists(@RequestParam(required = false) String author) {
        return ResponseEntity.ok(todoListService.findTodolists(author));
    }

    @GetMapping("/todolists/{todolistsId}")
    public ResponseEntity<TodoListResponse> getTodolists(
            @PathVariable Long todolistsId
    ) {
        return ResponseEntity.ok(todoListService.findTodolist(todolistsId));
    }

    @PutMapping("/todolists/{todolistsId}")
    public ResponseEntity<TodoListResponse> updateTodoList(
            @PathVariable Long todolistsId,
            @RequestBody TodoListRequest request
    ) {
        return ResponseEntity.ok(todoListService.updateTodoList(todolistsId, request));
    }

}
