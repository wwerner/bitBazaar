package de.bit.internal.bazaar;

import java.util.Date;

import com.google.appengine.api.users.UserServiceFactory;

import de.bit.internal.bazaar.model.Item;
import de.bit.internal.bazaar.model.ItemState;

public class ItemUtils {
	public static Item newItemTemplate() {
		Item newItem = new Item();
		newItem.setCreationDate(new Date(System.currentTimeMillis()));
		newItem.setAuthor(UserServiceFactory.getUserService().getCurrentUser());
		newItem.setState(ItemState.DRAFT);
		return newItem;
	}
}
