package ch.frankel.vaadin.workshop.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.DateRenderer;

public class MessageGrid extends Grid {

    MessageGrid(SQLContainer container) {
        GeneratedPropertyContainer genPropContainer = new GeneratedPropertyContainer(container);
        setContainerDataSource(genPropContainer);
        genPropContainer.addGeneratedProperty("delete", new PropertyValueGenerator<String>() {
            @Override
            public String getValue(Item item, Object itemId, Object propertyId) {
                return "Delete";
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        });
        setHeaderVisible(false);
        setSizeFull();
        setColumnOrder("TIME_STAMP", "AUTHOR", "TEXT", "delete");
        getColumn("TIME_STAMP").setWidth(100);
        getColumn("AUTHOR").setWidth(100);
        getColumn("delete").setWidth(100);
        getColumn("ID").setHidden(true);
        getColumn("TIME_STAMP").setRenderer(new DateRenderer("%1$ta. %1$tH:%1$tM:%1$tS"));
        getColumn("delete").setRenderer(new ButtonRenderer(e -> getContainerDataSource().removeItem(e.getItemId())));
        setSizeFull();
    }
}
