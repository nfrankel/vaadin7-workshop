package ch.frankel.vaadin.workshop.behavior;

import ch.frankel.vaadin.workshop.ui.MainScreen;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class LoginClickListener implements Button.ClickListener {

    @Override
    public void buttonClick(Button.ClickEvent event) {
        UI.getCurrent().setContent(new MainScreen());
        Notification.show("You've been successfully logged in");
    }
}
