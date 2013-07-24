package ch.frankel.duchessswiss.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;
import static com.vaadin.server.Sizeable.Unit.PIXELS;
import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.TOP_CENTER;

@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);

        setContent(mainLayout);

        Panel panel = new Panel();
        panel.setHeight(200, PIXELS);
        panel.setWidth(300, PIXELS);

        mainLayout.addComponent(panel);
        mainLayout.setComponentAlignment(panel, MIDDLE_CENTER);

        FormLayout panelLayout = new FormLayout();
        panelLayout.setMargin(true);
        panelLayout.setSpacing(true);
        panelLayout.setSizeFull();

        panel.setContent(panelLayout);

        TextField loginField = new TextField("Username");
        loginField.setWidth(100, PERCENTAGE);

        panelLayout.addComponent(loginField);

        Button button = new Button("Login");

        button.addClickListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {

                mainLayout.addComponent(new Label("Thank you for clicking"));
            }
        });

        panelLayout.addComponent(button);
    }
}
