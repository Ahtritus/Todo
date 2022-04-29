package org.incubyte.todo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceShould {

  @Mock
  TodoRepository todoRepository;

  @Test
  void invoke_save_method_in_repository() {
    TodoService todoService = new TodoService(todoRepository);

    Todo todo = new Todo();
    todoService.save(todo);

    verify(todoRepository).save(todo);
  }

  @Test
  void invoke_find_by_id_method_in_repository() {
    Todo dummyTodo = new Todo();
    dummyTodo.setDescription("Dummy");
    dummyTodo.setDone(false);

    when(todoRepository.findById(1L)).thenReturn(Optional.of(dummyTodo));
    TodoService todoService = new TodoService(todoRepository);

    todoService.findById(1L);

    verify(todoRepository).findById(1L);

  }

}