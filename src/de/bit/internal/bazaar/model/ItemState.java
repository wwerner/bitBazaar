package de.bit.internal.bazaar.model;

public enum ItemState {
	DRAFT("Entwurf"), ACTIVE("Aktiv"), SOLD("Verkauft");

	private String label;

	private ItemState(String label) {
		this.label = label;
	}

	public String toString() {
		return label;
	}
}
