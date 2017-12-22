package com.ccc.model.bean;

import java.io.Serializable;

public class ReportBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rep_id;
	private MemberBean member;
	private String rep_cause;
	private PostBean post;
	
	
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
	public int getRep_id() {
		return rep_id;
	}
	public void setRep_id(int rep_id) {
		this.rep_id = rep_id;
	}
	
	public String getRep_cause() {
		return rep_cause;
	}
	public void setRep_cause(String rep_cause) {
		this.rep_cause = rep_cause;
	}
}

