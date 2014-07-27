package ch.frankel.vaadin.workshop.behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;

public class SendMessageClickListener implements Button.ClickListener {

    private Table output;
    private TextArea input;

    public SendMessageClickListener(Table output, TextArea input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String message = input.getValue();
        output.addItem(new Object[] { message }, message.hashCode());
        input.setValue("");
    }
}
