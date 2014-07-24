package ch.frankel.vaadin.workshop.push;

public interface BroadcastListener<T> {

    void onMessage(T message);
}
