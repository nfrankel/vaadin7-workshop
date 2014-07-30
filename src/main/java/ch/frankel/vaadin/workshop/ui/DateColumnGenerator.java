package ch.frankel.vaadin.workshop.ui;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateColumnGenerator implements Table.ColumnGenerator {

    private static final String FORMAT = "dd/MM/yy HH:mm:ss";

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        Item item = source.getItem(itemId);
        Property<Date> property = item.getItemProperty(columnId);
        Date date = property.getValue();
        if (date == null) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.format(date);
    }
}
