package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.SendMessageClickListener;
import ch.frankel.vaadin.workshop.data.Message;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;
import static com.vaadin.ui.Table.ColumnHeaderMode.HIDDEN;

public class MainScreen extends CustomComponent {

    public MainScreen() {
        Label loginLabel = new Label("Welcome " + VaadinSession.getCurrent().getAttribute(String.class));
        HorizontalLayout menuBar = new HorizontalLayout(loginLabel);
        MessageTable table = new MessageTable();
        TextArea messageArea = new TextArea();
        messageArea.setWidth(100, PERCENTAGE);
        Button sendButton = new Button("Send");
        sendButton.addClickListener(new SendMessageClickListener(table, messageArea));
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
