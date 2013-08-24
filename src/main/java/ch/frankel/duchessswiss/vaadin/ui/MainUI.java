package ch.frankel.duchessswiss.vaadin.ui;

import ch.frankel.duchessswiss.vaadin.behavior.BroadcastListener;
import ch.frankel.duchessswiss.vaadin.behavior.Broadcaster;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Title("Duchess Vaadin demo")
@Push
public class MainUI extends UI implements BroadcastListener<Message> {

    private BroadcastListener<Message> broadcastListener;

    @Override
    public void onMessage(final Message message) {

        access(new Runnable() {

            @Override
            public void run() {

                broadcastListener.onMessage(message);
            }
        });
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MainUI.class)
    public static class Servlet extends VaadinServlet {
    }

    public static MainUI getCurrent() {

        return (MainUI) UI.getCurrent();
    }

    @Override
    protected void init(VaadinRequest request) {

        setContent(new LoginScreen());

        Broadcaster.getInstance().register(this);
    }

    @Override
    public void detach() {

        Broadcaster.getInstance().unregister(this);

        super.detach();
    }

    public void setBroadcastListener(BroadcastListener<Message> broadcastListener) {

        this.broadcastListener = broadcastListener;
    }
}
