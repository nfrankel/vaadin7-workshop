package ch.frankel.duchessswiss.vaadin.behavior;

public interface BroadcastListener<T> {

    void onMessage(T message);
}
