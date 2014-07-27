package ch.frankel.vaadin.workshop.ui;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class MainScreen extends CustomComponent {

    public MainScreen() {
        super(new Label(VaadinSession.getCurrent().getAttribute(String.class)));
    }
}
