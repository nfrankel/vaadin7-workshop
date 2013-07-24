package ch.frankel.duchessswiss.vaadin.behavior;

import com.vaadin.data.Property;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;

public class LoginListener implements Button.ClickListener {

    private Layout newScreen;
    private Property<String> loginHolder;

    public LoginListener(Layout newScreen, Property<String> loginHolder) {

        this.loginHolder = loginHolder;
        this.newScreen = newScreen;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        VaadinSession.getCurrent().setAttribute(String.class, loginHolder.getValue());

        UI.getCurrent().setContent(newScreen);
    }
}
