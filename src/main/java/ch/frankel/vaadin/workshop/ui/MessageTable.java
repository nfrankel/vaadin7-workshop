package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.data.Message;
import ch.frankel.vaadin.workshop.push.Broadcaster;
import com.vaadin.data.util.sqlcontainer.RowItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.QueryDelegate;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.ui.Table;

import java.sql.SQLException;

import static ch.frankel.vaadin.workshop.util.Parameters.*;
import static com.vaadin.ui.Table.ColumnHeaderMode.HIDDEN;

public class MessageTable extends Table {

    private final JDBCConnectionPool connectionPool;

    MessageTable() {
        try {
            connectionPool = new SimpleJDBCConnectionPool(
                    getDatabaseDriver(),
                    getDatabaseUrl(),
                    getDatabaseUsername(),
                    getDatabasePassword());
            QueryDelegate queryDelegate = new TableQuery("MESSAGE", connectionPool);
            SQLContainer container = new SQLContainer(queryDelegate);
            container.setAutoCommit(true);
            setContainerDataSource(container);
            setColumnHeaderMode(HIDDEN);
            addGeneratedColumn("TIME_STAMP", new DateColumnGenerator());
            addGeneratedColumn("DELETE", new DeleteColumnGenerator());
            setVisibleColumns("TIME_STAMP", "AUTHOR", "TEXT", "DELETE");
            setSizeFull();
            setColumnWidth("TIME_STAMP", 100);
            setColumnWidth("AUTHOR", 100);
            setColumnWidth("DELETE", 100);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMessage(Message message) {
        SQLContainer container = (SQLContainer) getContainerDataSource();
        Object rowId = container.addItem();
        RowItem rowItem = (RowItem) container.getItem(rowId);
        rowItem.getItemProperty("AUTHOR").setValue(message.getAuthor());
        rowItem.getItemProperty("TEXT").setValue(message.getText());
        rowItem.getItemProperty("TIME_STAMP").setValue(message.getTimeStamp());
        Broadcaster.broadcast(null);
    }

    @Override
    public void detach() {
        super.detach();
        connectionPool.destroy();
    }
}
