package ch.frankel.duchessswiss.vaadin.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

public class ChatScreen extends VerticalLayout {

    ChatScreen() {

        setSizeFull();
        setMargin(true);
        setSpacing(true);

        TextArea incoming = new TextArea();
        incoming.setSizeFull();
        addComponent(incoming);

        HorizontalLayout bottomBar = new HorizontalLayout();
        bottomBar.setWidth(100, PERCENTAGE);
        bottomBar.setSpacing(true);

        addComponent(bottomBar);

        TextArea toBeSent = new TextArea();
        toBeSent.setWidth(100, PERCENTAGE);
        bottomBar.addComponent(toBeSent);

        Button button = new Button("Send!");
        bottomBar.addComponent(button);
    }
}
