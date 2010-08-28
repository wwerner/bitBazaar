package de.bit.internal.bazaar.model;

import java.io.Serializable;
import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.datanucleus.jpa.annotations.Extension;

@SuppressWarnings("serial")
@Entity
public class Image implements Serializable {
	@Id
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	private URL url;
	private String caption;

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(id);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Image == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Image otherItem = (Image) obj;
		return new EqualsBuilder().append(id, otherItem.id).isEquals();
	}

}
