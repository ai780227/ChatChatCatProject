package com.ccc.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FavoriteBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private PostBean postBean;
	private MemberBean memberBean;

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof FavoriteBean)) {
			return false;
		}

		FavoriteBean item = (FavoriteBean) obj;
		return new EqualsBuilder()
				.append(this.postBean.getPost_id(), item.getPostBean().getPost_id())
				.append(this.memberBean.getM_id(), item.getMemberBean().getM_id()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.postBean.getPost_id())
				.append(this.memberBean.getM_id()).toHashCode();
	}

	public PostBean getPostBean() {
		return postBean;
	}

	public void setPostBean(PostBean postBean) {
		this.postBean = postBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
}