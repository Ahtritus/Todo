package org.incubyte.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceShould {

  @Mock
  TodoRepository todoRepository;


  Todo openTodo;
  Todo closedTodo;

  List<Todo> dummyTodos = new ArrayList<>();
  @BeforeEach
  public void init() {
    openTodo = new Todo();
    openTodo.setDone(false);
    openTodo.setId(1L);
    openTodo.setDescription("Open todo");
    dummyTodos.add(openTodo);

    closedTodo = new Todo();
    closedTodo.setDone(true);
    closedTodo.setId(2L);
    closedTodo.setDescription("Closed todo");
    dummyTodos.add(closedTodo);

  }

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

  @Test
  void find_all_todos_by_calling_repository() {
    TodoService todoService = new TodoService(todoRepository);

    Iterable<Todo> todos = todoService.getAllTodos();

    verify(todoRepository).findAllOrderById();
  }

  @Test
  void get_all_open_todos()
  {
    when(todoRepository.findAllOrderById()).thenReturn(dummyTodos);
    TodoService todoService = new TodoService(todoRepository);

    Iterable<Todo> todos = todoService.getAllTodosByFilter("open");

    verify(todoRepository).findAllOrderById();

    assertThat(todos).contains(openTodo);
  }

  @Test
  void get_all_closed_todos()
  {
    when(todoRepository.findAllOrderById()).thenReturn(dummyTodos);

    TodoService todoService = new TodoService(todoRepository);

    Iterable<Todo> todos = todoService.getAllTodosByFilter("closed");

    verify(todoRepository).findAllOrderById();

    assertThat(todos).contains(closedTodo);
  }

}