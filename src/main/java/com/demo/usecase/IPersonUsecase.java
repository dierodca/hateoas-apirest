package com.demo.usecase;

import java.util.List;

import com.demo.model.Person;

public interface IPersonUsecase {

  Person findById(final Integer id);

  List<Person> findAll();

  Person register(Person person);
}
