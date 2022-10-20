package com.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.model.Person;
import com.demo.usecase.IPersonUsecase;

@RestController
@RequestMapping(value = "/persons", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PersonaController {

  @Autowired
  private IPersonUsecase personUsecase;

  // Hateoas

  @GetMapping("/hateoas/{id}")
  public EntityModel<Person> hateoasFindById(@PathVariable("id") final Integer id) throws Exception {
    final EntityModel<Person> recurso = EntityModel.of(this.personUsecase.findById(id));
    recurso.add(linkTo(methodOn(PersonaController.class).hateoasFindById(id)).withSelfRel());
    return recurso;
  }

  @GetMapping("/hateoas")
  public CollectionModel<Person> hateoasFindAll() throws Exception {
    return CollectionModel.of(this.personUsecase.findAll(),
            linkTo(methodOn(PersonaController.class).hateoasFindAll()).withSelfRel());
  }

  @PostMapping("/hateoas")
  public ResponseEntity<Person> hateoasRegister(@RequestBody final Person person) {
    final Person personResponse = this.personUsecase.register(person);
    final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(personResponse.getId()).toUri();
    return ResponseEntity.created(location).body(personResponse);
  }

  // Normal

  @GetMapping("/{id}")
  public ResponseEntity<Person> findById(@PathVariable("id") final Integer id) {
    return ResponseEntity.ok(this.personUsecase.findById(id));
  }

  @GetMapping()
  public ResponseEntity<List<Person>> findAll() {
    final List<Person> response = this.personUsecase.findAll();
    if (response.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(response);
    }
  }

  @PostMapping()
  public ResponseEntity<Person> register(@RequestBody final Person person) {
    final Person personResponse = this.personUsecase.register(person);
    return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
  }

}
