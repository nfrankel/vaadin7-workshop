package ch.frankel.vaadin.workshop.behavior;

import ch.frankel.vaadin.workshop.ui.MainScreen;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

public class LoginClickListener implements Button.ClickListener {

    private TextField loginField;

    public LoginClickListener(TextField loginField) {
        this.loginField = loginField;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        VaadinSession.getCurrent().setAttribute(String.class, loginField.getValue());
        UI.getCurrent().setContent(new MainScreen());
    }
}
