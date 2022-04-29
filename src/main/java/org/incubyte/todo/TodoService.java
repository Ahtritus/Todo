package org.incubyte.todo;


import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo save(Todo todo) {
    return todoRepository.save(todo);
  }

  public Optional<Todo> findById(long id) {
    return todoRepository.findById(id);
  }

  public Iterable<Todo> getAllTodos() {
    return todoRepository.findAllOrderById();
  }

  public Iterable<Todo> getAllTodosByFilter(String status) {
    Iterable<Todo> todos = getAllTodos();

    List<Todo> allRequestedTodos = new ArrayList<>();

    for (Todo todo : todos) {
      if (todo.isDone() == status.equals("closed")) {
        allRequestedTodos.add(todo);
      }
    }

    return allRequestedTodos;
  }
}

