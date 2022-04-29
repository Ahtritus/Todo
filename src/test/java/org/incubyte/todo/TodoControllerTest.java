package org.incubyte.todo;

import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
class TodoControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  void should_save_todo_and_fetch_todo_by_id() {

    //Arrange
    Todo todo = new Todo();
    todo.setDescription("Remember the dummy todo description");

    //Acted
    Todo savedTodo = httpClient.toBlocking()
        .retrieve(HttpRequest.POST("todos/", todo), Argument.of(Todo.class));

    //Assert
    assertThat(savedTodo.getDescription()).isEqualTo(todo.getDescription());
    assertThat(savedTodo.isDone()).isFalse();
    assertThat(savedTodo.getId()).isPositive();

    //Arrange

    //Acted
    Todo retrievedTodo = httpClient.toBlocking()
        .retrieve((HttpRequest.GET("todos/" + savedTodo.getId())), Argument.of(Todo.class));

    //assert
    assertThat(retrievedTodo.getId()).isEqualTo(savedTodo.getId());
    assertThat(retrievedTodo.getDescription()).isEqualTo(savedTodo.getDescription());
    assertThat(retrievedTodo.isDone()).isEqualTo(savedTodo.isDone());
  }



}
