package com.example.demo.Service;

import com.example.demo.ApiException.ApiException;
import com.example.demo.AuthRepository.AuthRepository;
import com.example.demo.AuthRepository.TodoRepository;
import com.example.demo.Model.MyUser;
import com.example.demo.Model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
   @RequiredArgsConstructor
    public class TodoService {
    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;

    public List<Todo> getTodos(Integer id) {

        return todoRepository.findTodoByUserId(id);
    }

    public void addTodo(Integer MyuserId, Todo todo) {

        todo.setMyUser(new MyUser());
        todoRepository.save(todo);
    }

    public void updateTodo(Integer MyuserId, Todo todo, Integer todoId) {
        Todo oldTodo = todoRepository.findTodoById(todoId);
        if (oldTodo == null) {

            throw new ApiException("not found");
        }
        if (oldTodo.getId() != MyuserId) {
            throw new ApiException("error");
        }
        oldTodo.setMassage(todo.getMassage());
        todoRepository.save(oldTodo);
    }

    public void deleteTodo(Integer myuserId, Integer todoId) {
        Todo todo = todoRepository.findTodoById(todoId);
        if (todo.getId() == null) {

            throw new ApiException(" not found");
        }
        todoRepository.delete(todo);

    }

    public void assignTodoToUser(Integer myuser_id, Integer todo_id) {
        MyUser myUser = authRepository.findMyUserById(myuser_id);
        Todo todo = todoRepository.findTodoById(todo_id);
        if (myUser == null || todo == null) {
            throw new ApiException("not found");
        }
        todo.setMyUser(myUser);
        todoRepository.save(todo);
    }
}

