package de.bit.internal.bazaar.model;

import java.util.ArrayList;
import java.util.List;

public enum ItemState {
	DRAFT("Entwurf"), ACTIVE("Aktiv"), SOLD("Verkauft");

	private String label;

	private ItemState(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static List<String> valuesList() {
		List<String> labels = new ArrayList<String>(3);
		ItemState[] values = values();
		for (int i = 0; i < values.length; i++) {
			labels.add(values[i].getLabel());
		}
		return labels;
	}

}
