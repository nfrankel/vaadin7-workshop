package ch.frankel.duchessswiss.vaadin.ui;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeGeneratedColumn implements Table.ColumnGenerator {

    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy kk:mm");

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {

        Item item = source.getItem(itemId);

        Property<Date> prop = item.getItemProperty(columnId);

        return TIME_FORMAT.format(prop.getValue());
    }
}
