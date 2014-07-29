package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.SendMessageClickListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.Date;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

public class MainScreen extends CustomComponent {

    public MainScreen() {
        Label loginLabel = new Label("Welcome " + VaadinSession.getCurrent().getAttribute(String.class));
        HorizontalLayout menuBar = new HorizontalLayout(loginLabel);
        Grid grid = new Grid();
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("Author", String.class, null);
        container.addContainerProperty("Message", String.class, null);
        container.addContainerProperty("Date", Date.class, null);
        grid.setContainerDataSource(container);
        grid.setSizeFull();
        TextArea messageArea = new TextArea();
        messageArea.setWidth(100, PERCENTAGE);
        Button sendButton = new Button("Send");
        sendButton.addClickListener(new SendMessageClickListener(grid, messageArea));
        HorizontalLayout lowerBar = new HorizontalLayout(messageArea, sendButton);
        lowerBar.setWidth(100, PERCENTAGE);
        lowerBar.setSpacing(true);
        VerticalLayout mainLayout = new VerticalLayout(menuBar, grid, lowerBar);
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSizeFull();
        setCompositionRoot(mainLayout);
    }
}
