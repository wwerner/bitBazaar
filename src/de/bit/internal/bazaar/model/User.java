package de.bit.internal.bazaar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.datanucleus.jpa.annotations.Extension;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	private String userName;
	private String displayName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		// @formatter:off
		HashCodeBuilder hcb = new HashCodeBuilder()
			.append(id)
			.append(userName)
			.append(displayName);
		// @formatter:on
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final User otherItem = (User) obj;
		// @formatter:off
		return new EqualsBuilder()
			.append(id, otherItem.id)
			.append(userName, otherItem.userName)
			.append(displayName, otherItem.displayName)
			.isEquals();
		// @formatter:on
	}
}
