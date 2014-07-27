package ch.frankel.vaadin.workshop.behavior;

import ch.frankel.vaadin.workshop.ui.MainScreen;
import com.vaadin.ui.*;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

public class LoginClickListener implements Button.ClickListener {

    private TextField loginField;
    private PasswordField passwordField;

    public LoginClickListener(TextField loginField, PasswordField passwordField) {
        this.loginField = loginField;
        this.passwordField = passwordField;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if ("vaadin".equals(loginField.getValue())) {
            UI.getCurrent().setContent(new MainScreen());
            Notification.show("You've been successfully logged in");
        } else {
            Notification.show("Wrong credentials", ERROR_MESSAGE);
        }
    }
}
