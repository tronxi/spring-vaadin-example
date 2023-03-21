package com.example.springvaadinexample.domain.models;

import jakarta.validation.constraints.NotNull;

public class Contact {

  @NotNull
  private String name;
  private String surname;

  public Contact(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  public Contact() {
    this.name = null;
    this.surname = null;
  }

  public boolean contain(String partialName) {
    return name.contains(partialName);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public String toString() {
    return "Contact{" +
        "name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        '}';
  }
}
