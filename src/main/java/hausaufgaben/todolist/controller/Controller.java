package hausaufgaben.todolist.controller;

import hausaufgaben.todolist.domain.Todo;
import hausaufgaben.todolist.domain.Todolist;
import hausaufgaben.todolist.service.TodolistService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

  @Autowired
  TodolistService todolistService;

  @GetMapping("/todo")
  public String returnTodos(Model model) {
    Todolist todoList = todolistService.findTodos();
    model.addAttribute("todolist",todoList);
    model.addAttribute("todo",todoList.createDefault());
    return "todolist";
  }

  @PostMapping("/todo")
  public String addItem(String description, int priority, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime duedate) {
    todolistService.addTodo(description,priority,duedate);
    return "redirect:/todo";
  }

  @GetMapping("/todo/{id}")
  public String returnSingleTodo(Model model,@PathVariable("id") Long id) {
    Todo todo = todolistService.findTodo(id);
    model.addAttribute("todo",todo);
    return "singletodo";
  }

  @PatchMapping("/todo/{id}")
  public String setTodoToDone(@PathVariable("id") Long id) {
    todolistService.setTodoToDone(id);
    return "redirect:/todo";
  }

  @DeleteMapping("/todo/{id}")
  public String deleteSingleTodo(@PathVariable("id") Long id) {
    todolistService.deleteTodo(id);
    return "redirect:/todo";
  }



}
