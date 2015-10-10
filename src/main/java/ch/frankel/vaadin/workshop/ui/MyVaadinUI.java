package ch.frankel.vaadin.workshop.ui;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;
import ch.frankel.vaadin.workshop.util.EventBusUtils;
import ch.frankel.vaadin.workshop.util.RefreshEvent;

@SuppressWarnings("serial")
@Push
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new LoginScreen(new FormLayout()));
        EventBusUtils.singleton().register(this);
    }

    @Override
    public void detach() {
        EventBusUtils.singleton().unregister(this);
        super.detach();
    }

    @Subscribe
    public void onRefresh(RefreshEvent refreshEvent) {
        access(() -> {
            MainScreen content = (MainScreen) UI.getCurrent().getContent();
            content.getGrid().clearSortOrder();
        });
    }
}
