package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.LoginClickListener;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

public class LoginScreen extends CustomComponent {

    private AbstractOrderedLayout layout;

    public LoginScreen(AbstractOrderedLayout layout) {
        super(layout);
        this.layout = layout;
        layout.setMargin(true);
        Button button = new Button("Login");
        layout.addComponent(new Label("Please enter credentials"));
        TextField loginField = new TextField("Login:");
        loginField.setWidth(250, PIXELS);
        layout.addComponent(loginField);
        PasswordField passwordField = new PasswordField("Password:");
        layout.addComponent(passwordField);
        layout.addComponent(button);
        button.addClickListener(new LoginClickListener(loginField, passwordField));
    }
}
