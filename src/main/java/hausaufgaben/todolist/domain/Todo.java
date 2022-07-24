package hausaufgaben.todolist.domain;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Todo {

  @Id
  private Long id = null;
  private final String description;
  @Getter
  private boolean done = false;
  private final int priority;
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private final LocalDateTime duedate;

  public void changeDone() {
    this.done = true;
  }
}
