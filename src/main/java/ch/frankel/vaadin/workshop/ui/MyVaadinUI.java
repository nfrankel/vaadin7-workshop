package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.DummyClickListener;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

@SuppressWarnings("serial")
@Title("Vaadin Workshop")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final AbstractOrderedLayout layout = new FormLayout();
        layout.setMargin(true);
        setContent(layout);
        Button button = new Button("Click Me");
        button.addClickListener(new DummyClickListener(layout));
        layout.addComponent(new Label("Please enter credentials"));
        TextField login = new TextField("Login:");
        login.setWidth(250, PIXELS);
        layout.addComponent(login);
        layout.addComponent(new PasswordField("Password:"));
        layout.addComponent(button);
    }
}
