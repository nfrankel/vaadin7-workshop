package ch.frankel.duchessswiss.vaadin.behavior;

import ch.frankel.duchessswiss.vaadin.ui.Message;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;

import java.util.Date;

public class ChatListener implements Button.ClickListener, BroadcastListener<Message> {

    private TextArea source;
    private Table target;

    public ChatListener(TextArea source, Table target) {

        this.source = source;
        this.target = target;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        Message message = new Message(VaadinSession.getCurrent().getAttribute(String.class), source.getValue(), new Date());

        Broadcaster.getInstance().broadcast(message);
    }

    @Override
    public void onMessage(Message message) {

        target.getContainerDataSource(). addItem(message);

        source.setValue("");
    }
}
