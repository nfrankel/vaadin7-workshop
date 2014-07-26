package ch.frankel.vaadin.workshop.ui;

import com.vaadin.server.Page;
import ch.frankel.vaadin.workshop.behavior.DummyClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

@SuppressWarnings("serial")
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
        Page.getCurrent().setTitle("Vaadin workshop (" + System.currentTimeMillis() + ")");
    }
}
