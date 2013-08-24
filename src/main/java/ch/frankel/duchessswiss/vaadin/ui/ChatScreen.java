package ch.frankel.duchessswiss.vaadin.ui;

import static com.vaadin.ui.Table.ColumnHeaderMode.HIDDEN;
import ch.frankel.duchessswiss.vaadin.behavior.ChatListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

public class ChatScreen extends VerticalLayout {

    public ChatScreen() {

        setSizeFull();
        setMargin(true);
        setSpacing(true);

        Table incoming = new Table();
        incoming.setContainerDataSource(new BeanItemContainer(Message.class));
        incoming.setVisibleColumns("time", "sender", "text");
        incoming.setColumnWidth("time", 150);
        incoming.setColumnWidth("sender", 150);
        incoming.addGeneratedColumn("time", new TimeGeneratedColumn());
        incoming.setColumnHeaderMode(HIDDEN);
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
