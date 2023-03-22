package com.example.springvaadinexample.infrastructure.ui.pages.list;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import java.util.function.Consumer;

public class FilterComponent extends HorizontalLayout {

  public FilterComponent(Consumer<String> filter) {
    TextField filterText = new TextField();
    filterText.setPlaceholder("Filter by name...");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.EAGER);
    filterText.addValueChangeListener(event -> filter.accept(event.getValue()));
    add(filterText);
  }

}
