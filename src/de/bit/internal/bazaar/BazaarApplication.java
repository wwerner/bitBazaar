package de.bit.internal.bazaar;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.vaadin.Application;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.bit.internal.bazaar.data.JPAItemContainer;
import de.bit.internal.bazaar.model.ItemState;
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
	private Window main;

	private transient UserService userService;

	public BazaarApplication() {
		super();
	}

	@Override
	public void init() {
		main = new Window("bIT Bazaar");
		setMainWindow(main);

		userService = getUserService();
		setLogoutURL(userService.createLogoutURL("/login"));

		User user = userService.getCurrentUser();
		if (user == null) {
			unauthenticated();
		} else {
			authenticatedInit();
		}
	}

	private UserService getUserService() {
		if (userService == null)
			userService = UserServiceFactory.getUserService();
		return userService;
	}

	private void unauthenticated() {
		Window goToAuthWindow = new Window();
		Panel p = new Panel("Ne ne ne");
		Button b = new Button("Bitte anmelden, erstmal.");
		b.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
		p.addComponent(b);

		goToAuthWindow.addComponent(p);
		goToAuthWindow.setWidth("");

		VerticalLayout layout = (VerticalLayout) goToAuthWindow.getContent();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeUndefined();

		goToAuthWindow.center();
		main.addWindow(goToAuthWindow);
	}

	private void authenticatedInit() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		getItemTable().setValueChangeListener(this);

		JPAItemContainer dataSource= JPAItemContainer.allItems();
		dataSource.addContainerFilter("state", ItemState.ACTIVE.toString(), false, false);
		getItemTable().setDataSource(dataSource);
		getItemTable().init();

		SplitPanel content = new SplitPanel();
		content.setFirstComponent(getItemTable());
		content.setSecondComponent(getItemForm());
		content.setSplitPosition(40);

		layout.addComponent(createToolbar());
		layout.addComponent(content);
		layout.setExpandRatio(content, 1);
		main.setContent(layout);
	}

	public HorizontalLayout createToolbar() {
		HorizontalLayout lo = new HorizontalLayout();

		search.setWidth("300px");
		search.setInputPrompt("Suche");
		search.setImmediate(true);
		lo.addComponent(search);
		lo.addComponent(newest);
		lo.addComponent(newItem);

		Button myItems = new Button("Meine Einträge");
		myItems.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Filterable dataSource= (Filterable)getItemTable().getDataSource();
				dataSource.removeAllContainerFilters();
				dataSource.addContainerFilter("author", getUserService().getCurrentUser().getEmail(), false, false);
			}
		});
		lo.addComponent(myItems);
		
		Button allActiveItems = new Button("Alle aktiven Einträge");
		allActiveItems.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Filterable dataSource= (Filterable)getItemTable().getDataSource();
				dataSource.removeAllContainerFilters();
				dataSource.addContainerFilter("state", ItemState.ACTIVE.toString(), false, false);
			}
		});
		lo.addComponent(allActiveItems);

		Button logOut = new Button();
		logOut.setCaption(getUserService().getCurrentUser().getEmail() + " abmelden");
		lo.addComponent(logOut);

		newItem.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				ItemForm newItemForm = new ItemForm();
				newItemForm.setContainer(itemTable);
				de.bit.internal.bazaar.model.Item newItem = ItemUtils
						.newItemTemplate();

				newItemForm
						.setItemDataSource(new BeanItem<de.bit.internal.bazaar.model.Item>(
								newItem));
				newItemWindow = new Window("Neuer Eintrag");
				newItemWindow.setWidth("80%");
				newItemWindow.center();

				newItemWindow.addComponent(newItemForm);

				main.addWindow(newItemWindow);

			}
		});

		logOut.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});

		lo.setSpacing(true);
		return lo;
	}

	public ItemForm getItemForm() {
		if (itemForm == null) {
			itemForm = new ItemForm();
			itemForm.setContainer(getItemTable());
		}
		return itemForm;
	}

	public ItemTable getItemTable() {
		if (itemTable == null) {
			itemTable = new ItemTable();
		}
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
