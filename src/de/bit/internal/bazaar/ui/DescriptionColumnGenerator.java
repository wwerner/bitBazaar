package de.bit.internal.bazaar.ui;

import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public class DescriptionColumnGenerator implements ColumnGenerator {

	public Component generateCell(Table source, Object itemId, Object columnId) {
		Property prop = source.getItem(itemId).getItemProperty(columnId);
		if (prop != null && prop.getValue() != null) {
			Label label = new Label( (String)prop.getValue(),
					Label.CONTENT_XHTML);
			return label;
		}
		return null;
	}

}
