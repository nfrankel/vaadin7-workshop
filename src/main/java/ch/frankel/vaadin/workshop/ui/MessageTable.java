package ch.frankel.vaadin.workshop.ui;

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

    MessageTable() {
        try {
            JDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
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

    @Override
    public SQLContainer getContainerDataSource() {
        return (SQLContainer) super.getContainerDataSource();
    }
}
