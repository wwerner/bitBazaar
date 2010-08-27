package de.bit.internal.bazaar.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import de.bit.internal.bazaar.model.Item;

@SuppressWarnings("serial")
public class JPAItemContainer extends ItemDataContainer implements Serializable {

	public JPAItemContainer() {
		super(Item.class);
		EntityManagerFactory emf = BazaarEntityManagerFactory.get();
		EntityManager em = emf.createEntityManager();

		List<Item> items = em.createQuery("select i from Item i").getResultList();
		for (Item item : items) {
			addItem(item);
		}

		em.close();
	}
}
