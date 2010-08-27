package de.bit.internal.bazaar.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;

import de.bit.internal.bazaar.data.BazaarEntityManagerFactory;
import de.bit.internal.bazaar.model.Item;

@SuppressWarnings("serial")
public class ItemForm extends Form {
	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	public ItemForm() {
		setFormFieldFactory(new ItemFormFieldFactory());
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		setFooter(footer);
		
		save.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				EntityManagerFactory emf = BazaarEntityManagerFactory.get();
				EntityManager em = emf.createEntityManager();
				
				@SuppressWarnings("unchecked")
				BeanItem<Item> beanItem = (BeanItem<Item> ) getItemDataSource();
				em.merge(beanItem.getBean());
			}
		});
	}
}