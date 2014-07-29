package ch.frankel.vaadin.workshop.behavior;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;

import java.util.Date;

public class SendMessageClickListener implements Button.ClickListener {

    private Table output;
    private TextArea input;

    public SendMessageClickListener(Table output, TextArea input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String author = VaadinSession.getCurrent().getAttribute(String.class);
        String message = input.getValue();
        Date date = new Date();
        output.addItem(new Object[] { author, message, date }, message.hashCode());
        input.setValue("");
    }
}
