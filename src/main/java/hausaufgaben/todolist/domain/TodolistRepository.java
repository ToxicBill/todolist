package hausaufgaben.todolist.domain;

import java.util.List;
import java.util.Optional;

public interface TodolistRepository {

  List<Todo> findAll();

  Optional<Todo> findById(Long id);

  Todo save(Todo todo);

  void delete(Todo todo);

}
