package com.ccc.model.bean;

import java.io.Serializable;
import java.sql.Date;

public class BoardBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public BoardBean() {
	}
	  
	private int board_id;
	private String mgr_id;
	private java.sql.Timestamp board_time;
	private String board_content;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getMgr_id() {
		return mgr_id;
	}
	public void setMgr_id(String mgr_id) {
		this.mgr_id = mgr_id;
	}
	public java.sql.Timestamp getBoard_time() {
		return board_time;
	}
	public void setBoard_time(java.sql.Timestamp board_time) {
		this.board_time = board_time;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
}
