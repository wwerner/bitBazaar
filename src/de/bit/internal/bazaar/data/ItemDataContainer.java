package de.bit.internal.bazaar.data;

import java.util.Collection;

import com.vaadin.data.util.BeanItemContainer;

import de.bit.internal.bazaar.model.Item;

@SuppressWarnings("serial")
public abstract class ItemDataContainer extends BeanItemContainer<Item> {
	
	public ItemDataContainer(Class<Item> type) {
		super(type);
	}

	public ItemDataContainer(Collection<Item> collection)
			throws IllegalArgumentException {
		super(collection);
	}
}