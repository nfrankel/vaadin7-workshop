package ch.frankel.duchessswiss.vaadin.ui;

import ch.frankel.duchessswiss.vaadin.behavior.LoginListener;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;
import static com.vaadin.server.Sizeable.Unit.PIXELS;
import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

public class LoginScreen extends VerticalLayout {

    LoginScreen() {

        setSizeFull();
        setMargin(true);

        Panel panel = new Panel();
        panel.setHeight(200, PIXELS);
        panel.setWidth(300, PIXELS);

        addComponent(panel);
        setComponentAlignment(panel, MIDDLE_CENTER);

        final FormLayout panelLayout = new FormLayout();
        panelLayout.setMargin(true);
        panelLayout.setSpacing(true);
        panelLayout.setSizeFull();

        panel.setContent(panelLayout);

        final TextField loginField = new TextField("Username");
        loginField.setWidth(100, PERCENTAGE);

        panelLayout.addComponent(loginField);

        Button button = new Button("Login");

        button.addClickListener(new LoginListener(new ChatScreen(), loginField));

        panelLayout.addComponent(button);
    }
}
