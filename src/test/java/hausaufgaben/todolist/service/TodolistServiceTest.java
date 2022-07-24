package hausaufgaben.todolist.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hausaufgaben.todolist.domain.Todo;
import hausaufgaben.todolist.domain.Todolist;
import hausaufgaben.todolist.domain.TodolistRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TodolistServiceTest {

  @Mock
  TodolistRepository todolistRepository;


  public List<Todo> setUp() {
    List<Todo> list = new ArrayList<>();
    list.add(new Todo("Test1",1, LocalDateTime.now()));
    list.add(new Todo("Test1",2, LocalDateTime.now()));
    list.add(new Todo("Test1",3, LocalDateTime.now()));
    return list;
  }

  public Optional<Todo> setUpForId() {
    return Optional.of(new Todo("Test1", 1, LocalDateTime.now()));
  }

  @Test
  public void findTodoTest() {
    TodolistService todolistService = new TodolistService(todolistRepository);
    when(todolistRepository.findAll()).thenReturn(setUp());

    Todolist todolist = todolistService.findTodos();

    assertThat(todolist.getTodos().size()).isEqualTo(3);
  }

  @Test
  public void addTodoTest() {
    TodolistService todolistService = new TodolistService(todolistRepository);

    todolistService.addTodo("Test",1,LocalDateTime.now());

    verify(todolistRepository,times(1)).save(any());
  }

  @Test
  public void deleteTodoTest() {
    TodolistService todolistService = new TodolistService(todolistRepository);
    when(todolistRepository.findById(any())).thenReturn(setUpForId());
    Todolist todolist = todolistService.findTodos();

    todolistService.deleteTodo(any());

    verify(todolistRepository,times(1)).delete(any());
  }

  @Test
  public void setTodoDoneTest() {
    TodolistService todolistService = new TodolistService(todolistRepository);
    Optional<Todo> optionalTodo = setUpForId();
    when(todolistRepository.findById(any())).thenReturn(optionalTodo);
    Todolist todolist = todolistService.findTodos();

    todolistService.setTodoToDone(any());

    assertThat(optionalTodo.get().isDone()).isEqualTo(true);
    verify(todolistRepository,times(1)).save(any());
  }

}
