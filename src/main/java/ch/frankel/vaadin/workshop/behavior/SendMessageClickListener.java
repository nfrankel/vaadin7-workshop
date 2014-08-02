package ch.frankel.vaadin.workshop.behavior;

import ch.frankel.vaadin.workshop.data.Message;
import ch.frankel.vaadin.workshop.ui.MessageTable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;

import java.util.Date;

public class SendMessageClickListener implements Button.ClickListener {

    private MessageTable output;
    private TextArea input;

    public SendMessageClickListener(MessageTable output, TextArea input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String author = VaadinSession.getCurrent().getAttribute(String.class);
        String text = input.getValue();
        Date date = new Date();
        Message message = new Message(author, text, date);
        output.addMessage(message);
        input.setValue("");
    }
}
