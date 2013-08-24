package ch.frankel.duchessswiss.vaadin.behavior;

import ch.frankel.duchessswiss.vaadin.ui.Message;

import java.util.ArrayList;
import java.util.List;

public class Broadcaster {

    private final List<BroadcastListener<Message>> listeners = new ArrayList<BroadcastListener<Message>>();

    private static final Broadcaster SINGLETON = new Broadcaster();

    private Broadcaster() {}

    public static Broadcaster getInstance() {

        return SINGLETON;
    }

    public void register(BroadcastListener<Message> listener) {

        listeners.add(listener);
    }

    public void unregister(BroadcastListener<Message> listener) {

        listeners.remove(listener);
    }

    public void broadcast(Message message) {

        for (BroadcastListener<Message> listener : listeners) {

            listener.onMessage(message);
        }
    }
}
