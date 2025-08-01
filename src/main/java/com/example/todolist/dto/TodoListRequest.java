package com.example.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoListRequest {
    private String title;
    private String content;
    private String author;
    private String password;
}
