package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.SendMessageClickListener;
import ch.frankel.vaadin.workshop.data.Message;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.DateRenderer;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

public class MainScreen extends CustomComponent {

    public MainScreen() {
        Label loginLabel = new Label("Welcome " + VaadinSession.getCurrent().getAttribute(String.class));
        HorizontalLayout menuBar = new HorizontalLayout(loginLabel);
        Grid grid = new Grid();
        BeanItemContainer<Message> container = new BeanItemContainer<>(Message.class);
        GeneratedPropertyContainer genPropContainer = new GeneratedPropertyContainer(container);
        grid.setContainerDataSource(genPropContainer);
        genPropContainer.addGeneratedProperty("delete", new PropertyValueGenerator<String>() {
            @Override
            public String getValue(Item item, Object itemId, Object propertyId) {
                return "Delete";
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        });
        grid.setHeaderVisible(false);
        grid.setColumnOrder("timeStamp", "author", "text");
        grid.getColumn("timeStamp").setWidth(100);
        grid.getColumn("author").setWidth(100);
        grid.getColumn("timeStamp").setRenderer(new DateRenderer("%1$ta. %1$tH:%1$tM:%1$tS"));
        grid.getColumn("delete").setRenderer(new ButtonRenderer(e -> grid.getContainerDataSource().removeItem(e.getItemId())));
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
