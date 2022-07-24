package hausaufgaben.todolist.service;



import hausaufgaben.todolist.domain.Todo;
import hausaufgaben.todolist.domain.Todolist;
import hausaufgaben.todolist.domain.TodolistRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class TodolistService {


  private final TodolistRepository todoRepository;

  public TodolistService(TodolistRepository todolistRepository) {
    this.todoRepository = todolistRepository;
  }

  public Todolist createTodolist() {
    return new Todolist();
  }

  public Todolist findTodos() {
    List<Todo> todos = todoRepository.findAll();
    Todolist todolist = createTodolist();
    todolist.getTodos().addAll(todos);
    return todolist;

  }

  public Todo findTodo(Long id) {
    Optional<Todo> todo = todoRepository.findById(id);
    if(todo.isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
          "Das Todo mit id: " + id + "ist nicht vorhanden!");
    }
    else return todo.get();
  }

  public void addTodo(String task, int priority, LocalDateTime dueDate) {
    Todo todo = new Todo(task,priority,dueDate);
    todoRepository.save(todo);
  }

  public void deleteTodo(Long id) {
    Optional<Todo> todo = todoRepository.findById(id);
    if(todo.isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
          "Das Todo mit id: " + id + "ist nicht vorhanden!");
    }
    else {
      todoRepository.delete(todo.get());
    }
  }

  public void setTodoToDone(Long id) {
    Optional<Todo> todo = todoRepository.findById(id);
    if(todo.isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
          "Das Todo mit id: " + id + "ist nicht vorhanden!");
    }
    else {
      todo.get().changeDone();
      todoRepository.save(todo.get());
    }
  }
}
