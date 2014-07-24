package ch.frankel.vaadin.workshop.push;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Broadcaster implements Serializable {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static LinkedList<BroadcastListener<?>> listeners = new LinkedList<>();

    public static synchronized void register(BroadcastListener<?> listener) {
        listeners.add(listener);
    }

    public static synchronized void unregister(BroadcastListener<?> listener) {
        listeners.remove(listener);
    }

    public static synchronized void broadcast(final Object message) {
        for (final BroadcastListener listener : listeners) {
            executorService.execute(() -> listener.onMessage(message));
        }
    }

    public static synchronized void close() {
        if (!executorService.isShutdown() && !executorService.isTerminated()) {
            executorService.shutdownNow();
        }
    }
}
