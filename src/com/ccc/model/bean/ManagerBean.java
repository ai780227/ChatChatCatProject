package com.ccc.model.bean;

import java.io.Serializable;

public class ManagerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mgr_id;
	private byte[] mgr_pwd;
	private String mgr_name;
	private String mgr_email;
	
	public String getMgr_id() {
		return mgr_id;
	}
	public void setMgr_id(String mgr_id) {
		this.mgr_id = mgr_id;
	}
	public byte[] getMgr_pwd() {
		return mgr_pwd;
	}
	public void setMgr_pwd(byte[] mgr_pwd) {
		this.mgr_pwd = mgr_pwd;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	public String getMgr_email() {
		return mgr_email;
	}
	public void setMgr_email(String mgr_email) {
		this.mgr_email = mgr_email;
	}
	
}
