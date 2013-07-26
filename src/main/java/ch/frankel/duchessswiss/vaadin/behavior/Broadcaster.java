package ch.frankel.duchessswiss.vaadin.behavior;

import java.util.ArrayList;
import java.util.List;

public class Broadcaster {

    private final List<BroadcastListener<String, String>> listeners = new ArrayList<BroadcastListener<String, String>>();

    private static final Broadcaster SINGLETON = new Broadcaster();

    private Broadcaster() {}

    public static Broadcaster getInstance() {

        return SINGLETON;
    }

    public void register(BroadcastListener<String, String> listener) {

        listeners.add(listener);
    }

    public void unregister(BroadcastListener<String, String> listener) {

        listeners.remove(listener);
    }

    public void broadcast(String user, String message) {

        for (BroadcastListener<String, String> listener : listeners) {

            listener.onMessage(user, message);
        }
    }
}
