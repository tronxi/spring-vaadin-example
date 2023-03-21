package com.example.springvaadinexample.infrastructure.persistence;

import com.example.springvaadinexample.domain.models.Contact;
import com.example.springvaadinexample.domain.persistence.ContactRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MemoryContactRepository implements ContactRepository {
  private final List<Contact> contacts;

  public MemoryContactRepository() {
    contacts = new ArrayList<>();
    contacts.addAll(Arrays.asList(
        new Contact("pablo", "perez"),
        new Contact("sergio", "garcia"),
        new Contact("pablo", "roca"),
        new Contact("luis", "gonzalez"),
        new Contact("alba", "acevedo"),
        new Contact("irene", "vega"),
        new Contact("guillermo", "porras"),
        new Contact("jose", "antonio")
    ));
  }

  @Override
  public List<Contact> retrieve() {
    return contacts;
  }

  @Override
  public void save(Contact contact) {
    if(!contacts.contains(contact))
      contacts.add(contact);
  }

  @Override
  public void delete(Contact contact) {
    contacts.remove(contact);
  }
}
