package com.demo.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class Person extends RepresentationModel<Person> implements Serializable {

  private static final long serialVersionUID = -6781498255331703239L;

  private Integer id;
  private Integer age;
  private String name;

  public Person() {
  }

  public Person(final Person p) {
    this.id = p.getId();
    this.age = p.getAge();
    this.name = p.getName();
  }

  public Person(final Integer id, final Integer age, final String name) {
    this.id = id;
    this.age = age;
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return this.age;
  }

  public void setAge(final Integer age) {
    this.age = age;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person [id=" + this.id + ", age=" + this.age + ", name=" + this.name + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(this.age, this.id, this.name);
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final Person other = (Person) obj;
    return Objects.equals(this.age, other.age) && Objects.equals(this.id, other.id)
            && Objects.equals(this.name, other.name);
  }

}
