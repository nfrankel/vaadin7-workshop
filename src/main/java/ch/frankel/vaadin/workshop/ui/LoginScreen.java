package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.DummyClickListener;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

public class LoginScreen extends FormLayout {

    public LoginScreen() {
        setMargin(true);
        Button button = new Button("Click Me");
        button.addClickListener(new DummyClickListener(this));
        addComponent(new Label("Please enter credentials"));
        TextField login = new TextField("Login:");
        login.setWidth(250, PIXELS);
        addComponent(login);
        addComponent(new PasswordField("Password:"));
        addComponent(button);
    }
}
