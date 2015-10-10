package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.SendMessageClickListener;
import ch.frankel.vaadin.workshop.data.Message;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

public class MainScreen extends CustomComponent {

    public MainScreen() {
        Label loginLabel = new Label("Welcome " + VaadinSession.getCurrent().getAttribute(String.class));
        HorizontalLayout menuBar = new HorizontalLayout(loginLabel);
        Grid grid = new Grid();
        BeanItemContainer<Message> container = new BeanItemContainer<>(Message.class);
        grid.setContainerDataSource(container);
        grid.setHeaderVisible(false);
        grid.setSizeFull();
        TextArea messageArea = new TextArea();
        messageArea.setWidth(100, PERCENTAGE);
        Button sendButton = new Button("Send");
        sendButton.addClickListener(new SendMessageClickListener(container, messageArea));
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
