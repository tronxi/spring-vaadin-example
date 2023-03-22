package com.example.springvaadinexample.infrastructure.ui.pages.list;

import com.example.springvaadinexample.domain.models.Contact;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import java.util.List;
import java.util.function.Consumer;

public class GridComponent extends Div {

  private final Grid<Contact> grid;

  public GridComponent(List<Contact> contacts, Consumer<Contact> onSelect) {
    grid = new Grid<>(Contact.class);
    addClassNames("contact-grid");
    grid.setSizeFull();
    grid.setColumns("name", "surname");
    grid.addColumn(contact -> contact.getName() + "@" + contact.getSurname())
        .setHeader("header")
        .setSortable(true);
    grid.getColumns().forEach(col -> col.setAutoWidth(true));
    grid.setItems(contacts);
    grid.asSingleSelect().addValueChangeListener(event -> onSelect.accept(event.getValue()));
    add(grid);
  }

  public void update(List<Contact> contacts) {
    grid.setItems(contacts);
  }

}
