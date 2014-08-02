package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.push.BroadcastListener;
import ch.frankel.vaadin.workshop.push.Broadcaster;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Title("Vaadin Workshop")
@PreserveOnRefresh
@Push
public class MyVaadinUI extends UI implements BroadcastListener<Object> {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new LoginScreen(new FormLayout()));
        Broadcaster.register(this);
    }

    @Override
    public void onMessage(Object object) {
        access(() -> setContent(new MainScreen()));
    }

    @Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }
}
