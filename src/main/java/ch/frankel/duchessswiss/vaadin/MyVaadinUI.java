package ch.frankel.duchessswiss.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout layout = new VerticalLayout();

        layout.setMargin(true);

        setContent(layout);

        TextField loginField = new TextField("Login");

        layout.addComponent(loginField);

        Button button = new Button("Click Me");

        button.addClickListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {

                layout.addComponent(new Label("Thank you for clicking"));
            }
        });

        layout.addComponent(button);
    }
}
