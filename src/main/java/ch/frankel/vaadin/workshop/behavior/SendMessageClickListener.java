package ch.frankel.vaadin.workshop.behavior;

import com.vaadin.data.util.sqlcontainer.RowItem;
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
        Object rowId = output.addItem();
        RowItem rowItem = (RowItem) output.getItem(rowId);
        rowItem.getItemProperty("AUTHOR").setValue(author);
        rowItem.getItemProperty("TEXT").setValue(text);
        rowItem.getItemProperty("TIME_STAMP").setValue(date);
        input.setValue("");
    }
}
