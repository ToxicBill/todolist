package hausaufgaben.todolist.repositories;

import hausaufgaben.todolist.domain.Todo;
import hausaufgaben.todolist.domain.TodolistRepository;

import org.springframework.data.repository.CrudRepository;


public interface JdbcTodolistRepository extends CrudRepository<Todo,Long>, TodolistRepository {


}
