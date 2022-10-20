package com.demo.model;

import java.io.Serializable;

public class Person implements Serializable {

  private static final long serialVersionUID = -6781498255331703239L;

  private Integer id;
  private Integer age;
  private String name;

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

}
