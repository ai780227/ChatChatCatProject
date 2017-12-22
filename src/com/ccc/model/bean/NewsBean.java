package com.ccc.model.bean;

import java.io.Serializable;
import java.sql.Date;

public class NewsBean implements Serializable{
	private static final long serialVersionUID = 1L;
	

	public NewsBean() {
	}
	
	private int news_id;
	private String mgr_id;
	private String news_title;
	private java.sql.Timestamp news_time;
	private String news_content;
	private String news_source;
	private String news_link;
	
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getMgr_id() {
		return mgr_id;
	}
	public void setMgr_id(String mgr_id) {
		this.mgr_id = mgr_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public java.sql.Timestamp getNews_time() {
		return news_time;
	}
	public void setNews_time(java.sql.Timestamp news_time) {
		this.news_time = news_time;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getNews_source() {
		return news_source;
	}
	public void setNews_source(String news_source) {
		this.news_source = news_source;
	}
	public String getNews_link() {
		return news_link;
	}
	public void setNews_link(String news_link) {
		this.news_link = news_link;
	}
	
}
