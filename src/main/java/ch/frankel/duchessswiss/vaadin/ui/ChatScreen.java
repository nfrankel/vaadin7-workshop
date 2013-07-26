package ch.frankel.duchessswiss.vaadin.ui;

import ch.frankel.duchessswiss.vaadin.behavior.Broadcaster;
import ch.frankel.duchessswiss.vaadin.behavior.ChatListener;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

public class ChatScreen extends VerticalLayout {

    public ChatScreen() {

        setSizeFull();
        setMargin(true);
        setSpacing(true);

        TextArea incoming = new TextArea();
        incoming.setSizeFull();
        incoming.setReadOnly(true);
        addComponent(incoming);

        HorizontalLayout bottomBar = new HorizontalLayout();
        bottomBar.setWidth(100, PERCENTAGE);
        bottomBar.setSpacing(true);

        addComponent(bottomBar);

        TextArea toBeSent = new TextArea();
        toBeSent.setWidth(100, PERCENTAGE);
        bottomBar.addComponent(toBeSent);

        Button button = new Button("Send!");

        ChatListener chatListener = new ChatListener(toBeSent, incoming);

        button.addClickListener(chatListener);

        MainUI.getCurrent().setBroadcastListener(chatListener);

        bottomBar.addComponent(button);
    }
}
