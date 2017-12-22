package com.ccc.model.bean;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WhoLikeBean implements Serializable {

	private PostBean post;
	private MemberBean member;
	private String m_name;
	
	public PostBean getPost() {
		return post;
	}

	public void setPost(PostBean post) {
		this.post = post;
	}

	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}
	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
 
        if(!(obj instanceof WhoLikeBean)) {
            return false;
        }
 
        WhoLikeBean user = (WhoLikeBean) obj;
        return new EqualsBuilder()
                    .append(this.post, user.getPost())
                    .append(this.member, user.getMember())
                    .isEquals();
    }
 
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(this.member)
                    .append(this.post)
                    .toHashCode();
    }

}
