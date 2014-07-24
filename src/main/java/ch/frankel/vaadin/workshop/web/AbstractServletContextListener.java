package ch.frankel.vaadin.workshop.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public abstract class AbstractServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {}

    @Override
    public void contextDestroyed(ServletContextEvent event) {}
}
