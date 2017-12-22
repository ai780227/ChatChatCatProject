package com.ccc.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PictureBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int pic_id;
//	private int c_id;	//private CatBean cat;
//	private Integer c_id;
//	private String m_id;	//private MemberBean member;
	private String pic_file_path;
	private java.sql.Timestamp pic_time;
	private Set<PostPictureBean> postPictures = new HashSet<PostPictureBean>();

	//關聯  Picture to Member 多對一
	private MemberBean member;
	public MemberBean getMember() {
		return member;
	}
	public void setMember(MemberBean member) {
		this.member = member;
	}
	//關聯  Picture to Member 多對一
	private CatBean cat;
	public CatBean getCat() {
		return cat;
	}
	public void setCat(CatBean cat) {
		this.cat = cat;
	}

	public int getPic_id() {
		return pic_id;
	}
	public void setPic_id(int pic_id) {
		this.pic_id = pic_id;
	}
/*	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public Integer getC_id() {
		return this.c_id;
	}*/
/*	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}*/
	public String getPic_file_path() {
		return pic_file_path;
	}
	public void setPic_file_path(String pic_file_path) {
		this.pic_file_path = pic_file_path;
	}
	public java.sql.Timestamp getPic_time() {
		return pic_time;
	}
	public void setPic_time(java.sql.Timestamp pic_time) {
		this.pic_time = pic_time;
	}

	// one-to-many PostPictureBean
	public Set<PostPictureBean> getPostPictures() {
		return postPictures;
	}

	public void setPostPictures(Set<PostPictureBean> postPictures) {
		this.postPictures = postPictures;
	}
}
