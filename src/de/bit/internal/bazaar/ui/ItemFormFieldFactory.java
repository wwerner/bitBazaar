package de.bit.internal.bazaar.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

import de.bit.internal.bazaar.model.User;

@SuppressWarnings("serial")
public class ItemFormFieldFactory extends DefaultFieldFactory {

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		String pid = (String) propertyId;

		if (pid.equals("id")) {
			return null;
		}
		if (pid.equals("description")) {
			return new RichTextArea(createCaptionByPropertyId(propertyId));
		}

		return super.createField(item, propertyId, uiContext);
	}

}
