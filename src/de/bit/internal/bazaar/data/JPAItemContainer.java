package de.bit.internal.bazaar.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.appengine.api.users.UserServiceFactory;
import com.vaadin.data.Container.Filterable;

import de.bit.internal.bazaar.model.Item;
import de.bit.internal.bazaar.model.ItemState;

@SuppressWarnings("serial")
public class JPAItemContainer extends ItemDataContainer implements Serializable, Filterable {

	private JPAItemContainer(String query) {
		super(Item.class);
		EntityManagerFactory emf = BazaarEntityManagerFactory.get();
		EntityManager em = emf.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Item> items = em.createQuery(query).getResultList();
		for (Item item : items) {
			addItem(item);
		}

		em.close();
	}

	public static JPAItemContainer allItems() {
		return new JPAItemContainer("select i from Item i");
	}

	public static JPAItemContainer activeItems() {
		return new JPAItemContainer("select i from Item i where i.state = '"
				+ ItemState.ACTIVE.name() + "'");
	}

	public static JPAItemContainer userItems() {
		return new JPAItemContainer("select i from Item i where i.author = '"
				+ UserServiceFactory.getUserService().getCurrentUser()
						.getEmail() + "'");
	}

}
