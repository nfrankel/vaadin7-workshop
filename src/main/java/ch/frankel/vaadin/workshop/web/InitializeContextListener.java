package ch.frankel.vaadin.workshop.web;

import ch.frankel.vaadin.workshop.util.Parameters;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static ch.frankel.vaadin.workshop.util.Parameters.*;

@WebListener
public class InitializeContextListener extends AbstractServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("workshop.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            Parameters.init(properties);
        } catch (IOException e) {
            System.out.println("Couldn't close stream on workshop.properties");
        }
        Connection connection = null;
        try {
            Class.forName(getDatabaseDriver());
            connection = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            Statement createStatement = connection.createStatement();
            createStatement.execute("CREATE TABLE IF NOT EXISTS MESSAGE(" +
                    "ID BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "TEXT VARCHAR(255)," +
                    "AUTHOR VARCHAR(255)," +
                    "`TIME_STAMP` TIME);");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Couldn't close connection");
                }
            }
        }
    }
}
