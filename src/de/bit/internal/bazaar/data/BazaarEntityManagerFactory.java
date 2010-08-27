package de.bit.internal.bazaar.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class BazaarEntityManagerFactory {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("bazaar");

	private BazaarEntityManagerFactory() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}