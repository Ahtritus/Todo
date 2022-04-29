package org.incubyte.todo;

import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;

@MicronautTest
@Transactional
class TodoControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  void todo_crud_and_filtering() {

    //Arrange
    Todo todo1 = new Todo();
    todo1.setDescription("Dummy Todo 1");
    todo1.setDone(false);

    Todo todo2 = new Todo();
    todo2.setDescription("Dummy Todo 2");
    todo2.setDone(true);

    /***
     * test to check save todos
     */
    //Act
    Todo savedTodo1 = this.httpClient.toBlocking()
        .retrieve(HttpRequest.POST("/todos", todo1), Argument.of(Todo.class));
    Todo savedTodo2 = this.httpClient.toBlocking()
        .retrieve(HttpRequest.POST("/todos", todo2), Argument.of(Todo.class));

    //Assert
    assertThat(savedTodo1.getDescription()).isEqualTo(todo1.getDescription());
    assertThat(savedTodo1.isDone()).isFalse();
    assertThat(savedTodo1.getId()).isPositive();

    assertThat(savedTodo2.getDescription()).isEqualTo(todo2.getDescription());
    assertThat(savedTodo2.isDone()).isTrue();
    assertThat(savedTodo2.getId()).isPositive();

    /***
     * test to check get todos by id
     */
    //Act
    Todo retrievedTodo = httpClient.toBlocking()
        .retrieve((HttpRequest.GET("todos/" + savedTodo1.getId())), Argument.of(Todo.class));

    //Assert
    assertThat(retrievedTodo.getId()).isEqualTo(savedTodo1.getId());
    assertThat(retrievedTodo.getDescription()).isEqualTo(savedTodo1.getDescription());
    assertThat(retrievedTodo.isDone()).isEqualTo(savedTodo1.isDone());

    /***
     * test to check get all todos
     */
    //Act
    List<Todo> retrivedTodoList = httpClient.toBlocking().retrieve(
        HttpRequest.GET("todos/"), Argument.listOf(Todo.class));

    //Assert
    assertThat(retrivedTodoList).containsExactly(savedTodo1, savedTodo2);

    /***
     * test to check get all todos with open status
     */
    //Act
    List<Todo> retrivedOpenTodoList = httpClient.toBlocking().retrieve(
        HttpRequest.GET("todos/open"), Argument.listOf(Todo.class));

    //Assert
    assertThat(retrivedOpenTodoList).containsExactly(savedTodo1);

    /***
     * test to check get all todos with closed status
     */
    //Act
    List<Todo> retrivedCloseTodoList = httpClient.toBlocking().retrieve(
        HttpRequest.GET("todos/closed"), Argument.listOf(Todo.class));

    //Assert
    assertThat(retrivedCloseTodoList).containsExactly(savedTodo2);

  }


}
