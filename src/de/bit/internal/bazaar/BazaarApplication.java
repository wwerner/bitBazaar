package de.bit.internal.bazaar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.bit.internal.bazaar.data.BazaarEntityManagerFactory;
import de.bit.internal.bazaar.data.JPAItemContainer;
import de.bit.internal.bazaar.model.User;
import de.bit.internal.bazaar.ui.ItemForm;
import de.bit.internal.bazaar.ui.ItemTable;

@SuppressWarnings("serial")
public class BazaarApplication extends Application implements
		Property.ValueChangeListener {

	private Button newItem = new Button("Neuer Eintrag");
	private TextField search = new TextField();
	private Button newest = new Button("Neueste Einträge");
	
	private Window newItemWindow;

	private ItemForm itemForm;

	private ItemTable itemTable;

	public BazaarApplication() {
		super();
	}

	@Override
	public void init() {
		dataTest();
		final Window main = new Window("bIT Bazaar");
		setMainWindow(main);

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		getItemTable().setDataSource(new JPAItemContainer());
		getItemTable().setValueChangeListener(this);
		getItemTable().init();

		SplitPanel content = new SplitPanel();
		content.setFirstComponent(getItemTable());
		content.setSecondComponent(getItemForm());
		content.setSplitPosition(40);

		layout.addComponent(createToolbar());
		layout.addComponent(content);
		layout.setExpandRatio(content, 1);
		main.setContent(layout);
		
		newItem.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				ItemForm newItemForm = new ItemForm();
				de.bit.internal.bazaar.model.Item newItem = new de.bit.internal.bazaar.model.Item();
				
				newItemForm.setItemDataSource(new BeanItem<de.bit.internal.bazaar.model.Item>( newItem));
				newItemWindow = new Window("Neuer Eintrag");
				newItemWindow.center();
				
				newItemWindow.addComponent(newItemForm);
				
				main.addWindow(newItemWindow);
				
			}
		});

	}

	private void dataTest() {
		de.bit.internal.bazaar.model.Item i = new de.bit.internal.bazaar.model.Item();
		i.setTitle("test");
		i.setDescription("this is the <i>description</i>");
		User u = new User();
		u.setDisplayName("Hannes");
		u.setUserName("a@b.com");
		i.setAuthor(u);

		EntityManagerFactory emf = BazaarEntityManagerFactory.get();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.persist(i);
		em.getTransaction().commit();

		em.close();

	}

	public HorizontalLayout createToolbar() {
		HorizontalLayout lo = new HorizontalLayout();

		search.setWidth("300px");
		search.setInputPrompt("Suche");
		search.setImmediate(true);
		lo.addComponent(search);
		lo.addComponent(newest);
		lo.addComponent(newItem);

		lo.setSpacing(true);
		return lo;
	}

	public ItemForm getItemForm() {
		if (itemForm == null)
			itemForm = new ItemForm();
		return itemForm;
	}

	public ItemTable getItemTable() {
		if (itemTable == null)
			itemTable = new ItemTable();
		return itemTable;
	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == itemTable) {
			Item item = itemTable.getItem(itemTable.getValue());
			if (item != itemForm.getItemDataSource()) {
				itemForm.setItemDataSource(item);
			}
		}
	}
}
