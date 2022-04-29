package org.incubyte.todo;


import jakarta.inject.Singleton;

@Singleton
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo save(Todo todo) {
    return todoRepository.save(todo);
  }

  public Todo findById(long id) {
    return todoRepository.findById(id).get();
  }
}

