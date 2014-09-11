package ch.frankel.vaadin.workshop.util;

import com.google.common.eventbus.EventBus;

public class EventBusUtils {

    private static final EventBus EVENT_BUS = new EventBus();

    public static EventBus singleton() {
        return EVENT_BUS;
    }
}
