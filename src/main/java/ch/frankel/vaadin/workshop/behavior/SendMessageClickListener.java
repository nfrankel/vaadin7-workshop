package ch.frankel.vaadin.workshop.behavior;

import com.vaadin.data.Item;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;

import java.util.Date;

public class SendMessageClickListener implements Button.ClickListener {

    private SQLContainer output;
    private TextArea input;

    public SendMessageClickListener(SQLContainer output, TextArea input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String author = VaadinSession.getCurrent().getAttribute(String.class);
        String text = input.getValue();
        Date date = new Date();
        Object itemId = output.addItem();
        Item item = output.getItem(itemId);
        item.getItemProperty("AUTHOR").setValue(author);
        item.getItemProperty("TEXT").setValue(text);
        item.getItemProperty("TIME_STAMP").setValue(date);
        input.setValue("");
    }
}
