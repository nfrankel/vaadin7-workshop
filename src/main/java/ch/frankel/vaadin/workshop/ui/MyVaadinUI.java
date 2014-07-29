package ch.frankel.vaadin.workshop.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Title("Vaadin Workshop")
@PreserveOnRefresh
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new LoginScreen(new FormLayout()));
    }
}
