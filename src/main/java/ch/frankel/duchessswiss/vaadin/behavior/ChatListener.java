package ch.frankel.duchessswiss.vaadin.behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;

public class ChatListener implements Button.ClickListener {

    private TextArea source;
    private TextArea target;

    public ChatListener(TextArea source, TextArea target) {

        this.source = source;
        this.target = target;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        String message = source.getValue();

        StringBuilder alreadyThere = new StringBuilder(target.getValue());

        alreadyThere.append("\n");

        alreadyThere.append(message);

        target.setValue(alreadyThere.toString());
        source.setValue("");
    }
}
