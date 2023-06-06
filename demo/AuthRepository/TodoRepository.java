package com.example.demo.AuthRepository;

import com.example.demo.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findTodoByUserId(Integer userid);
    Todo findTodoById(Integer id);

}
