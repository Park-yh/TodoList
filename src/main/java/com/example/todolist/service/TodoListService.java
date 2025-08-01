package com.example.todolist.service;

import com.example.todolist.dto.TodoListRequest;
import com.example.todolist.dto.TodoListResponse;
import com.example.todolist.entity.TodoList;
import com.example.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public List<TodoListResponse> findTodolists(@RequestParam(required = false) String author) {
        List<TodoList> todoLists;
        if(author != null && !author.isEmpty()){
            todoLists = todoListRepository.findByAuthorOrderByModifiedAtDesc(author);
        } else{
            todoLists = todoListRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedAt"));
        }
        return todoLists.stream()
                .map(todoList -> new TodoListResponse(
                        todoList.getId(),
                        todoList.getTitle(),
                        todoList.getContent(),
                        todoList.getAuthor(),
                        todoList.getCreatedAt(),
                        todoList.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public TodoListResponse findTodolist(Long todolistId){
        TodoList todoList = todoListRepository.findById(todolistId).orElseThrow(
                () -> new IllegalArgumentException("TodoListId not found!")
        );
        return new TodoListResponse(
                todoList.getId(),
                todoList.getTitle(),
                todoList.getContent(),
                todoList.getAuthor(),
                todoList.getCreatedAt(),
                todoList.getModifiedAt()
        );
    }

}
