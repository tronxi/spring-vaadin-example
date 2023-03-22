package com.example.springvaadinexample.infrastructure.ui;

import com.example.springvaadinexample.infrastructure.ui.pages.dashboard.DashboardPage;
import com.example.springvaadinexample.infrastructure.ui.pages.list.ListPage;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

  public MainLayout() {
    createHeader();
    createDrawer();
  }

  private void createHeader() {
    H1 logo = new H1("Vaadin CRM");

    Button button = new Button("Dashboard");
    button.addClickListener(buttonClickEvent -> button.getUI().ifPresent(ui -> ui.navigate(DashboardPage.class)));

    HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, button);
    header.setDefaultVerticalComponentAlignment( FlexComponent.Alignment.CENTER);
    header.setWidth("100%");
    header.addClassName("header");

    addToNavbar(header);
  }

  private void createDrawer() {
    RouterLink listLink = new RouterLink("List", ListPage.class);
    RouterLink dashboardLink = new RouterLink("Dashboard", DashboardPage.class);
    listLink.setHighlightCondition(HighlightConditions.sameLocation());
    addToDrawer(new VerticalLayout(listLink, dashboardLink));
  }

}
