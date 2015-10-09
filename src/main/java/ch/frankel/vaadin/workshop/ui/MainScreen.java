package ch.frankel.vaadin.workshop.ui;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot
public class MainScreen extends VerticalLayout {

    public MainScreen() {
        Design.read("main-screen.html", this);
    }
}
