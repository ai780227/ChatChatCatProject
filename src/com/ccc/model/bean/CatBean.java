package com.ccc.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CatBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer c_id;
//	private String m_id;	//private MemberBean Member;
	private String c_name;
	private String c_age;
	private String c_breed;
	private String c_sex;
	private String c_intro;
	private String c_pic_path;	//private PictureBean picture;
	
	//關聯  Cat to Picture 一對多
		private Set<PictureBean> pictures = new HashSet<PictureBean>();
		public Set<PictureBean> getPictures() {
			return pictures;
		}
		public void setPictures(Set<PictureBean> pictures) {
			this.pictures = pictures;
		}
	//關聯  Cat to Member 多對一
	private MemberBean member;
	public MemberBean getMember() {
		return member;
	}
	public void setMember(MemberBean member) {
		this.member = member;
	}
	
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
/*	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}*/
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_age() {
		return c_age;
	}
	public void setC_age(String c_age) {
		this.c_age = c_age;
	}
	public String getC_breed() {
		return c_breed;
	}
	public void setC_breed(String c_breed) {
		this.c_breed = c_breed;
	}
	public String getC_sex() {
		return c_sex;
	}
	public void setC_sex(String c_sex) {
		this.c_sex = c_sex;
	}
	public String getC_intro() {
		return c_intro;
	}
	public void setC_intro(String c_intro) {
		this.c_intro = c_intro;
	}
	public String getC_pic_path() {
		return c_pic_path;
	}
	public void setC_pic_path(String c_pic_path) {
		this.c_pic_path = c_pic_path;
	}	
}
