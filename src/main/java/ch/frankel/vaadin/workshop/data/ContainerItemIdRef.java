package ch.frankel.vaadin.workshop.data;

import com.vaadin.data.Container;

import java.io.Serializable;

public class ContainerItemIdRef implements Serializable {

    private Container container;
    private Object itemId;

    public ContainerItemIdRef(Container container, Object itemId) {
        this.container = container;
        this.itemId = itemId;
    }

    public Container getContainer() {
        return container;
    }

    public Object getItemId() {
        return itemId;
    }
}
