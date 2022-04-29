package org.incubyte.todo;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

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
  public Todo findById(long id) {
    return todoService.findById(id);
  }
}
