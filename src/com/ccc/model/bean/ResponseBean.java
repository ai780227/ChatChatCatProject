package com.ccc.model.bean;

import java.util.HashSet;
import java.util.Set;

public class ResponseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int res_id;
	private PostBean postBean;
	private MemberBean memberBean;	// 回覆者
	private String res_content;
	private java.sql.Timestamp res_time;
	private Set<ResponseNoticeBean> responseNotice = new HashSet<ResponseNoticeBean>();

	public int getRes_id() {
		return res_id;
	}

	public void setRes_id(int res_id) {
		this.res_id = res_id;
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

	public String getRes_content() {
		return res_content;
	}

	public void setRes_content(String res_content) {
		this.res_content = res_content;
	}

	public java.sql.Timestamp getRes_time() {
		return res_time;
	}

	public void setRes_time(java.sql.Timestamp res_time) {
		this.res_time = res_time;
	}

	// one-to-many ResponseNoticeBean
	public Set<ResponseNoticeBean> getResponseNotice() {
		return responseNotice;
	}

	public void setResponseNotice(Set<ResponseNoticeBean> responseNotice) {
		this.responseNotice = responseNotice;
	}
}