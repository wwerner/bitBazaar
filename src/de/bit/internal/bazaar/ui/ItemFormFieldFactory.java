package de.bit.internal.bazaar.ui;

import java.util.Arrays;
import java.util.List;

import com.google.appengine.repackaged.com.google.common.base.Pair;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;

import de.bit.internal.bazaar.model.ItemState;

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
		if (pid.equals("state")) {
			@SuppressWarnings("unchecked")
			ComboBox c =new ComboBox(createCaptionByPropertyId(propertyId),Arrays.asList(ItemState.values()));
			c.setNullSelectionAllowed(false);
			return c;
		}

		return super.createField(item, propertyId, uiContext);
	}

}
