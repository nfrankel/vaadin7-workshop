package ch.frankel.vaadin.workshop.ui;

import java.sql.SQLException;
import ch.frankel.vaadin.workshop.behavior.SendMessageClickListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.QueryDelegate;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;
import static ch.frankel.vaadin.workshop.util.Parameters.*;


public class MainScreen extends CustomComponent {

    public MainScreen() {
        Label loginLabel = new Label("Welcome " + VaadinSession.getCurrent().getAttribute(String.class));
        HorizontalLayout menuBar = new HorizontalLayout(loginLabel);
        try {
            JDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
                getDatabaseDriver(),
                getDatabaseUrl(),
                getDatabaseUsername(),
                getDatabasePassword());
            QueryDelegate queryDelegate = new TableQuery("MESSAGE", connectionPool);
            SQLContainer container = new SQLContainer(queryDelegate);
            container.setAutoCommit(true);
            MessageGrid grid = new MessageGrid(container);
            TextArea messageArea = new TextArea();
            messageArea.setWidth(100, PERCENTAGE);
            Button sendButton = new Button("Send");
            sendButton.addClickListener(new SendMessageClickListener(container, messageArea));
            HorizontalLayout lowerBar = new HorizontalLayout(messageArea, sendButton);
            lowerBar.setWidth(100, PERCENTAGE);
            lowerBar.setSpacing(true);
            VerticalLayout mainLayout = new VerticalLayout(menuBar, grid, lowerBar);
            mainLayout.setSpacing(true);
            mainLayout.setMargin(true);
            mainLayout.setSizeFull();
            setCompositionRoot(mainLayout);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
