package ch.frankel.vaadin.workshop.behavior;

import ch.frankel.vaadin.workshop.data.ContainerItemIdRef;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;

public class DeleteMessageClickListener implements Button.ClickListener {

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Button button = event.getButton();
        ContainerItemIdRef data = (ContainerItemIdRef) button.getData();
        Object itemId = data.getItemId();
        Container container = data.getContainer();
        container.removeItem(itemId);
    }
}
