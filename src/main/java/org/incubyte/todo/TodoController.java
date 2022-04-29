package org.incubyte.todo;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.util.Optional;

@Controller("/todos")
public class TodoController {

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  private final TodoService todoService;

  @Post("/")
  public Todo save(Todo todo) {

    return todoService.save(todo);
  }

  @Get("/{id}")
  public Optional<Todo> findById(long id) {
    return todoService.findById(id);
  }

  @Get(value = "/")
  public Iterable<Todo> getAllTodos() {
    return todoService.getAllTodos();
  }

  @Get("/open")
  public Iterable<Todo> getAllOpenTodos() {
    return todoService.getAllTodosByFilter("open");
  }

  @Get("/closed")
  public Iterable<Todo> getAllCloseTodos() {
    return todoService.getAllTodosByFilter("closed");
  }
}
