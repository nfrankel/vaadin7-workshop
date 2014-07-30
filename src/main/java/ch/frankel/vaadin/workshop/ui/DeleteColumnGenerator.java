package ch.frankel.vaadin.workshop.ui;

import ch.frankel.vaadin.workshop.behavior.DeleteMessageClickListener;
import ch.frankel.vaadin.workshop.data.ContainerItemIdRef;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;

public class DeleteColumnGenerator implements Table.ColumnGenerator {

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        Button button = new Button("Delete");
        button.setData(new ContainerItemIdRef(source.getContainerDataSource(), itemId));
        button.addClickListener(new DeleteMessageClickListener());
        return button;
    }
}
