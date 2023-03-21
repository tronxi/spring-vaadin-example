package com.example.springvaadinexample.domain.usecases;

import com.example.springvaadinexample.domain.models.Contact;
import com.example.springvaadinexample.domain.persistence.ContactRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {

  private final ContactRepository contactRepository;

  public ContactsService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public List<Contact> retrieve() {
    return contactRepository.retrieve();
  }

  public void save(Contact contact) {
    contactRepository.save(contact);
  }

  public void delete(Contact contact) {
    contactRepository.delete(contact);
  }

}
