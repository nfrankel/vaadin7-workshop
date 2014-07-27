package ch.frankel.vaadin.workshop.behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;

public class SendMessageClickListener implements Button.ClickListener {

    private Grid output;
    private TextArea input;

    public SendMessageClickListener(Grid output, TextArea input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String message = input.getValue();
        output.addRow(message);
        input.setValue("");
    }
}
