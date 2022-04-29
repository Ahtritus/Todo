package org.incubyte.todo;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoControllerShould {

  @Mock
  TodoService todoService;

  @Test
  void call_save_method_from_service() {
    TodoController todoController = new TodoController(todoService);

    Todo todo = new Todo();
    todoController.save(todo);

    verify(todoService).save(todo);
  }

  @Test
  void return_saved_todo_by_id() {
    TodoController todoController = new TodoController(todoService);

    Todo todo = new Todo();

    todoController.findById(1L);

    verify(todoService).findById(1L);
  }

  @Test
  void call_service_to_get_all_the_todos() {
    TodoController todoController = new TodoController(todoService);

    Iterable<Todo> todos =  todoController.getAllTodos();

    verify(todoService).getAllTodos();
  }

  @Test
  void get_all_open_todos()
  {
    TodoController todoController = new TodoController(todoService);

    Iterable<Todo> todos =  todoController.getAllOpenTodos();

    verify(todoService).getAllTodosByFilter("open");
  }

  @Test
  void get_all_closed_todos()
  {
    TodoController todoController = new TodoController(todoService);

    Iterable<Todo> todos =  todoController.getAllCloseTodos();

    verify(todoService).getAllTodosByFilter("closed");
  }
}
