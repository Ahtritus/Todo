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
}
