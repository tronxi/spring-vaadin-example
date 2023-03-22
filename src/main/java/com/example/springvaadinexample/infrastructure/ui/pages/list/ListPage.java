package com.example.springvaadinexample.infrastructure.ui.pages.list;

import com.example.springvaadinexample.domain.models.Contact;
import com.example.springvaadinexample.domain.usecases.ContactsService;
import com.example.springvaadinexample.infrastructure.ui.pages.list.ContactFormComponent.CloseEvent;
import com.example.springvaadinexample.infrastructure.ui.pages.list.ContactFormComponent.DeleteEvent;
import com.example.springvaadinexample.infrastructure.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Contacts | Vaadin CRM")
public class ListPage extends VerticalLayout {

  private final ContactsService contactsService;
  private final GridComponent gridComponent;
  private final ContactFormComponent contactFormComponent;
  private List<Contact> contacts;

  public ListPage(ContactsService contactsService) {
    this.contactsService = contactsService;

    addClassName("list-view");
    setSizeFull();

    contacts = contactsService.retrieve();
    gridComponent = new GridComponent(contacts, this::onSelected);
    contactFormComponent = new ContactFormComponent(this::onSave);
    contactFormComponent
        .addListener(CloseEvent.class, closeEvent -> contactFormComponent.setVisible(false));
    contactFormComponent.addListener(DeleteEvent.class, deleteEvent -> onDelete(deleteEvent.getContact()));
    contactFormComponent.setVisible(false);
    Div content = new Div();
    content.setSizeFull();
    content.addClassName("content");
    content.add(gridComponent, contactFormComponent);

    HorizontalLayout toolbar = new HorizontalLayout();
    Button addButton = new Button("add");
    addButton.addClickListener(buttonClickEvent -> onSelected(new Contact()));
    toolbar.add(new FilterComponent(this::filter), addButton);

    add(toolbar, content);
  }

  private void onDelete(Contact contact) {
    contactsService.delete(contact);
    contacts = contactsService.retrieve();
    gridComponent.update(contacts);
    contactFormComponent.setVisible(false);
  }

  private void onSelected(Contact contact) {
    contactFormComponent.setVisible(true);
    contactFormComponent.setContact(contact);
  }

  private void onSave(Contact contact) {
    contactsService.save(contact);
    contacts = contactsService.retrieve();
    gridComponent.update(contacts);
    contactFormComponent.setVisible(false);
  }

  private void filter(String name) {
    if (name.isEmpty()) {
      gridComponent.update(contacts);
    } else {
      gridComponent.update(
          contacts
              .stream()
              .filter(contact -> contact.contain(name))
              .toList()
      );
    }
  }

}
