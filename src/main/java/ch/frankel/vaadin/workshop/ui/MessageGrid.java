package ch.frankel.vaadin.workshop.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.DateRenderer;
import ch.frankel.vaadin.workshop.data.Message;

public class MessageGrid extends Grid {

    MessageGrid(BeanItemContainer<Message> container) {
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
        setColumnOrder("timeStamp", "author", "text");
        getColumn("timeStamp").setWidth(100);
        getColumn("author").setWidth(100);
        getColumn("timeStamp").setRenderer(new DateRenderer("%1$ta. %1$tH:%1$tM:%1$tS"));
        getColumn("delete").setRenderer(new ButtonRenderer(e -> getContainerDataSource().removeItem(e.getItemId())));
        setSizeFull();
    }
}
