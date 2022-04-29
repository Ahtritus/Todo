package org.incubyte.todo;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {

  @Id
  @GeneratedValue
  private Long id;

  private String description;
  private boolean done;

  public Todo() {
    // default constructor required for hibernate
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isDone() {
    return done;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Todo todo = (Todo) o;
    return id == todo.id && done == todo.done && description.equals(todo.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, done);
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
