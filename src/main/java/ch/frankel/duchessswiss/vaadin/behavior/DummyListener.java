package ch.frankel.duchessswiss.vaadin.behavior;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public class DummyListener implements Button.ClickListener {

    private Layout parent;
    private Property<String> child;

    public DummyListener(Layout parent, Property<String> child) {

        this.child = child;
        this.parent = parent;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        parent.addComponent(new Label(child.getValue()));
    }
}
