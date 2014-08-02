package ch.frankel.vaadin.workshop.behavior;

import ch.frankel.vaadin.workshop.ui.MainScreen;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

public class LoginClickListener implements Button.ClickListener {

    private TextField loginField;
    private PasswordField passwordField;

    public LoginClickListener(TextField loginField, PasswordField passwordField) {
        this.loginField = loginField;
        this.passwordField = passwordField;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        VaadinSession.getCurrent().setAttribute(String.class, loginField.getValue());
        UI.getCurrent().setContent(new MainScreen());
        Notification.show("You've been successfully logged in");
    }
}
