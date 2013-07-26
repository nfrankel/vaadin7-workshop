package ch.frankel.duchessswiss.vaadin.behavior;

public interface BroadcastListener<T, V> {

    void onMessage(T user, V message);
}
