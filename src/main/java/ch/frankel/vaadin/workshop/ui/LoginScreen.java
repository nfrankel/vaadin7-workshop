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
        button.addClickListener(new LoginClickListener());
        layout.addComponent(new Label("Please enter credentials"));
        TextField login = new TextField("Login:");
        login.setWidth(250, PIXELS);
        layout.addComponent(login);
        layout.addComponent(new PasswordField("Password:"));
        layout.addComponent(button);
    }
}
