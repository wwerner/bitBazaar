package de.bit.internal.bazaar.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.appengine.api.users.User;

@SuppressWarnings("serial")
@Entity
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date creationDate;
	private String title;
	private String description;

	private User author;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Image> images;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Tag> tags;
	private Double price;

	@Enumerated
	private ItemState state;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

/*	public String getState() {
		if (state == null)
			return StringUtils.EMPTY;
		return state.getLabel();
	}

	public void setState(String state) {
		if (ItemState.ACTIVE.getLabel().equals(state)) {
			this.state = ItemState.ACTIVE;
		} else if (ItemState.DRAFT.getLabel().equals(state)) {
			this.state = ItemState.DRAFT;
		} else if (ItemState.SOLD.getLabel().equals(state)) {
			this.state = ItemState.SOLD;
		} else
			throw new IllegalArgumentException(
					"Only the following values are valid: "
							+ ItemState.valuesList());
	}
*/
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorDisplayName() {
		if (author != null)
			return author.getEmail();
		return null;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(id);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Item otherItem = (Item) obj;
		return new EqualsBuilder().append(id, otherItem.id).isEquals();
	}

	public ItemState getState() {
		return state;
	}

	public void setState(ItemState state) {
		this.state = state;
	}
}
