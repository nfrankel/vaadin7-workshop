package ch.frankel.vaadin.workshop.behavior;

import ch.frankel.vaadin.workshop.ui.MainScreen;
import com.vaadin.ui.*;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

public class LoginClickListener implements Button.ClickListener {

    private TextField loginField;

    public LoginClickListener(TextField loginField) {
        this.loginField = loginField;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if ("vaadin".equals(loginField.getValue())) {
            UI.getCurrent().setContent(new MainScreen());
            Notification.show("You've been successfully logged in");
        } else {
            Notification.show("Wrong credentials", ERROR_MESSAGE);
        }
    }
}
