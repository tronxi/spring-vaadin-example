package com.example.springvaadinexample.infrastructure.ui.pages.dashboard;

import com.example.springvaadinexample.infrastructure.ui.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardPage extends VerticalLayout {

  public DashboardPage() {
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    add(new H1("Otra página"));
  }
}
