package de.bit.internal.bazaar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.datanucleus.jpa.annotations.Extension;

@SuppressWarnings("serial")
@Entity
public class Tag implements Serializable {
	@Id
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(tag);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tag == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Tag otherItem = (Tag) obj;
		// @formatter:off
		return new EqualsBuilder()
			.append(tag, otherItem.tag)
			.isEquals();
		// @formatter:on
	}
}
