package de.bit.internal.bazaar.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@Entity
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date creationDate;
	private String title;
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	private User author;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Image> images;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Tag> tags;
	private Double price;
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

	public ItemState getState() {
		return state;
	}

	public void setState(ItemState state) {
		this.state = state;
	}

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
			return author.getDisplayName();
		return null;
	}

	@Override
	public int hashCode() {
		// @formatter:off
		HashCodeBuilder hcb = new HashCodeBuilder()
			.append(id)
			.append(title)
			.append(creationDate)
			.append(description)
			.append(author)
			.append(images)
			.append(tags)
			.append(price)
			.append(state);
		// @formatter:on
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
		// @formatter:off
		return new EqualsBuilder()
			.append(id, otherItem.id)
			.append(title, otherItem.title)
			.append(creationDate, otherItem.creationDate)
			.append(description, otherItem.description)
			.append(author, otherItem.author)
			.append(images, otherItem.images)
			.append(tags, otherItem.tags)
			.append(price, otherItem.price)
			.append(state, otherItem.state)
			.isEquals();
		// @formatter:on
	}
}
