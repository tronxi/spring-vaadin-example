package com.example.springvaadinexample.infrastructure.api.rest;

import com.example.springvaadinexample.domain.models.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ContactController {

  @GetMapping()
  public ResponseEntity<Contact> test() {
    return ResponseEntity.ok(new Contact("hola", "guay"));
  }

}
