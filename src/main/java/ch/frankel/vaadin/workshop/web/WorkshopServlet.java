package ch.frankel.vaadin.workshop.web;

import ch.frankel.vaadin.workshop.ui.MyVaadinUI;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
public class WorkshopServlet extends VaadinServlet {
}
