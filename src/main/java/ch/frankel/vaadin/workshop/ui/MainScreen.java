package ch.frankel.vaadin.workshop.ui;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

public class MainScreen extends CustomComponent {

    public MainScreen() {
        Label loginLabel = new Label("Welcome " + VaadinSession.getCurrent().getAttribute(String.class));
        HorizontalLayout menuBar = new HorizontalLayout(loginLabel);
        Table table = new Table();
        table.setSizeFull();
        TextArea messageArea = new TextArea();
        messageArea.setWidth(100, PERCENTAGE);
        Button sendButton = new Button("Send");
        HorizontalLayout lowerBar = new HorizontalLayout(messageArea, sendButton);
        lowerBar.setWidth(100, PERCENTAGE);
        lowerBar.setSpacing(true);
        VerticalLayout mainLayout = new VerticalLayout(menuBar, table, lowerBar);
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSizeFull();
        setCompositionRoot(mainLayout);
    }
}
