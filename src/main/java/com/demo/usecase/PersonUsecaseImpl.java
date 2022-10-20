package com.demo.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.demo.model.Person;

@Service
public class PersonUsecaseImpl implements IPersonUsecase {

  private List<Person> personsList;

  @PostConstruct
  public void init() {
    this.personsList = new ArrayList<>();
  }

  @Override
  public Person findById(final Integer id) {
    final Optional<Person> personOptional = this.personsList.stream().filter(person -> person.getId().equals(id))
            .findFirst();
    if (personOptional.isPresent()) {
      return personOptional.get();
    } else {
      throw new NullPointerException("La persona con id: ".concat(id.toString()).concat(" no existe"));
    }
  }

  @Override
  public List<Person> findAll() {
    return this.personsList;
  }

  @Override
  public Person register(final Person person) {

    if (!this.personsList.contains(person)) {
      this.personsList.add(person);
    }
    return person;
  }

}
