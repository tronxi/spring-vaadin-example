package com.example.springvaadinexample.domain.persistence;

import com.example.springvaadinexample.domain.models.Contact;
import java.util.List;

public interface ContactRepository {
  List<Contact> retrieve();
  void save(Contact contact);
  void delete(Contact contact);
}
