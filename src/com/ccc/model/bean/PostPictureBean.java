package com.ccc.model.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PostPictureBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PostBean postBean;
	private PictureBean pictureBean;

	public PostBean getPostBean() {
		return postBean;
	}

	public void setPostBean(PostBean postBean) {
		this.postBean = postBean;
	}

	public PictureBean getPictureBean() {
		return pictureBean;
	}

	public void setPictureBean(PictureBean pictureBean) {
		this.pictureBean = pictureBean;
	}

	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(!(obj instanceof PostPictureBean)) {
			return false;
		}
		PostPictureBean item = (PostPictureBean) obj;
		return new EqualsBuilder().append(this.pictureBean.getPic_id(), item.getPictureBean().getPic_id())
								  .append(this.postBean.getPost_id(), item.getPostBean().getPost_id())
								  .isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder()
					.append(this.pictureBean.getPic_id())
					.append(this.postBean.getPost_id())
					.toHashCode();
	}
}
