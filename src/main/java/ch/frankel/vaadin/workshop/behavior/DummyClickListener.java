package ch.frankel.vaadin.workshop.behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

public class DummyClickListener implements Button.ClickListener {

    private Layout layout;

    public DummyClickListener(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Label label = new Label("Thank you for clicking");
        label.setHeight(100, PIXELS);
        layout.addComponent(label);
    }
}
