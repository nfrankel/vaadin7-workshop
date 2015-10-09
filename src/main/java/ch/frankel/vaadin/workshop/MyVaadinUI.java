package ch.frankel.vaadin.workshop;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

@SuppressWarnings("serial")
@Title("Vaadin Workshop")
public class MyVaadinUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final AbstractOrderedLayout layout = new FormLayout();
        layout.setMargin(true);
        setContent(layout);
        Button button = new Button("Click Me");
        button.addClickListener(event -> {
            Label label = new Label("Thank you for clicking");
            label.setHeight(100, PIXELS);
            layout.addComponent(label);
        });
        layout.addComponent(new Label("Please enter credentials"));
        TextField login = new TextField("Login:");
        login.setWidth(250, PIXELS);
        layout.addComponent(login);
        layout.addComponent(new PasswordField("Password:"));
        layout.addComponent(button);
    }
}
