package com.example.springvaadinexample.infrastructure.ui.pages.list;

import com.example.springvaadinexample.domain.models.Contact;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import java.util.function.Consumer;

public class ContactFormComponent extends FormLayout {

  private final Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

  private Contact contact;

  public ContactFormComponent(Consumer<Contact> onSave) {
    TextField name = new TextField("Nombre");
    TextField surname = new TextField("Apellidos");

    Button saveButton = new Button("Edit");
    saveButton.addClickListener(buttonClickEvent -> onSave.accept(pressSave()));

    Button deleteButton = new Button("Delete");
    deleteButton.addClickListener(buttonClickEvent -> onPressDelete());

    Button closeButton = new Button("Close");
    closeButton.addClickListener(buttonClickEvent -> fireEvent(new CloseEvent(this)));

    addClassName("contact-form");

    binder
        .forField(name)
        .withValidator(s -> s.length() != 0, "Cannot be empty")
        .bind(Contact::getName, Contact::setName);

    binder.bind(surname, Contact::getSurname, Contact::setSurname);
    binder.addStatusChangeListener(
        valueChangeEvent -> setButtonEnabled(saveButton, binder.isValid()));

    saveButton.setEnabled(binder.isValid());

    HorizontalLayout buttonsLayout = new HorizontalLayout();
    buttonsLayout.add(saveButton, deleteButton, closeButton);

    add(name, surname, buttonsLayout);
  }

  public void setContact(Contact contact) {
    this.contact = contact;
    binder.readBean(contact);
  }

  private void setButtonEnabled(Button button, boolean isValid) {
    button.setEnabled(isValid);
  }

  private Contact pressSave() {
    try {
      binder.writeBean(contact);
      return contact;
    } catch (ValidationException e) {
      return null;
    }
  }

  private void onPressDelete() {
    fireEvent(new DeleteEvent(this, contact));
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
      ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }

  public static abstract class ContactFormEvent extends ComponentEvent<ContactFormComponent> {

    private final Contact contact;

    protected ContactFormEvent(ContactFormComponent source, Contact contact) {
      super(source, false);
      this.contact = contact;
    }

    public Contact getContact() {
      return contact;
    }
  }

  public static class DeleteEvent extends ContactFormEvent {
    DeleteEvent(ContactFormComponent source, Contact contact) {
      super(source, contact);
    }
  }

  public static class CloseEvent extends ContactFormEvent {
    CloseEvent(ContactFormComponent source) {
      super(source, null);
    }
  }

}
