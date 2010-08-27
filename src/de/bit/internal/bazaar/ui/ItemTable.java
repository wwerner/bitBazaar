package de.bit.internal.bazaar.ui;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

import de.bit.internal.bazaar.data.ItemDataContainer;
import de.bit.internal.bazaar.model.User;

@SuppressWarnings("serial")
public class ItemTable extends Table {
	private static final int MAX_DESCRIPTION_LENGTH = 10;

	public Object[] NATURAL_COL_ORDER = new Object[] { "title", "description",
			"price", "state", "authorDisplayName"};

	public String[] COL_HEADERS_GERMAN = new String[] { "Titel",
			"Beschreibung", "Preis", "Status", "Verkäufer" };

	Property.ValueChangeListener valueChangeListener;
	ItemDataContainer dataSource;

	public void init() {
		setContainerDataSource(dataSource);
		setSelectable(true);
		setImmediate(true);
		addListener((Property.ValueChangeListener) valueChangeListener);
		setNullSelectionAllowed(false);
		setColumnReorderingAllowed(true);
		setColumnCollapsingAllowed(true);

		 addGeneratedColumns();
		

		setVisibleColumns(NATURAL_COL_ORDER);
		setColumnHeaders(COL_HEADERS_GERMAN);
		setSizeFull();
	}

	@Override
	protected String formatPropertyValue(Object rowId, Object colId,
			Property property) {
		if(colId.equals("description" )) {
			return super.formatPropertyValue(rowId, colId, property);
		}
		return super.formatPropertyValue(rowId, colId, property);
	}
	
	

	@Override
	protected Object getPropertyValue(Object rowId, Object colId,
			Property property) {
		if(colId.equals("description" )) {
			return super.getPropertyValue(rowId, colId, property);
		}
		return super.getPropertyValue(rowId, colId, property);
	}

	private void addGeneratedColumns() {
		addGeneratedColumn("description", new DescriptionColumnGenerator());
	}

	public Container getDataSource() {
		return dataSource;
	}

	public void setDataSource(ItemDataContainer dataSource) {
		this.dataSource = dataSource;
	}

	public Property.ValueChangeListener getValueChangeListener() {
		return valueChangeListener;
	}

	public void setValueChangeListener(
			Property.ValueChangeListener valueChangeListener) {
		this.valueChangeListener = valueChangeListener;
	}

}