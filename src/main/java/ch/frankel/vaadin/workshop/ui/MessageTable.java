package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.data.Message;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

import static com.vaadin.ui.Table.ColumnHeaderMode.HIDDEN;

public class MessageTable extends Table {

    MessageTable() {
        BeanItemContainer<Message> container = new BeanItemContainer<>(Message.class);
        setContainerDataSource(container);
        setColumnHeaderMode(HIDDEN);
        addGeneratedColumn("timeStamp", new DateColumnGenerator());
        addGeneratedColumn("delete", new DeleteColumnGenerator());
        setVisibleColumns("timeStamp", "author", "text", "delete");
        setSizeFull();
        setColumnWidth("timeStamp", 100);
        setColumnWidth("author", 100);
        setColumnWidth("delete", 100);
    }

    @Override
    public BeanItemContainer<Message> getContainerDataSource() {
        return (BeanItemContainer<Message>) super.getContainerDataSource();
    }
}
