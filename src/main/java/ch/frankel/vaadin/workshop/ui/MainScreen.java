package ch.frankel.vaadin.workshop.ui;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainScreen extends VerticalLayout {

    public MainScreen() {
        super(new Label(VaadinSession.getCurrent().getAttribute(String.class)));
    }
}
