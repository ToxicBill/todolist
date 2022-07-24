package hausaufgaben.todolist.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Todolist {

  private List<Todo> todos = new ArrayList<>();

  public List<Todo> getHighPriority() {
    return todos.stream()
        .filter(x -> x.getPriority() >= 7)
        .sorted(Comparator.comparingInt(Todo::getPriority).reversed())
        .collect(Collectors.toList());
  }

  public List<Todo> getMediumPriority() {
    return todos.stream()
        .filter(x -> x.getPriority() < 7 && x.getPriority() > 3)
        .sorted(Comparator.comparingInt(Todo::getPriority).reversed())
        .collect(Collectors.toList());
  }

  public List<Todo> getLowPriority() {
    return todos.stream()
        .filter(x -> x.getPriority() <= 3)
        .sorted(Comparator.comparingInt(Todo::getPriority).reversed())
        .collect(Collectors.toList());
  }

  public Todo createDefault() {
    return new Todo("Beschreibung",0, LocalDateTime.now());
  }
}
