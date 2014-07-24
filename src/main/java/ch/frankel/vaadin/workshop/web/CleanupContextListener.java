package ch.frankel.vaadin.workshop.web;

import ch.frankel.vaadin.workshop.push.Broadcaster;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class CleanupContextListener extends AbstractServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Broadcaster.close();
    }
}
