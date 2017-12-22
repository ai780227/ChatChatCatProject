package com.ccc.model.bean;

public class ResponseNoticeBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int res_notid;
	private String m_id_to;		// 接收者ID
	private String m_id_from;	// 回覆者ID
	private String m_name_from;	// 回覆者的名稱
	private int post_id;
	private ResponseBean responseBean;
	private int res_read;		// 0未讀/1已讀

	public int getRes_notid() {
		return res_notid;
	}

	public void setRes_notid(int res_notid) {
		this.res_notid = res_notid;
	}

	public String getM_id_to() {
		return m_id_to;
	}

	public void setM_id_to(String m_id_to) {
		this.m_id_to = m_id_to;
	}

	public String getM_id_from() {
		return m_id_from;
	}

	public void setM_id_from(String m_id_from) {
		this.m_id_from = m_id_from;
	}

	public String getM_name_from() {
		return m_name_from;
	}

	public void setM_name_from(String m_name_from) {
		this.m_name_from = m_name_from;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public ResponseBean getResponseBean() {
		return responseBean;
	}

	public void setResponseBean(ResponseBean responseBean) {
		this.responseBean = responseBean;
	}

	public int getRes_read() {
		return res_read;
	}

	public void setRes_read(int res_read) {
		this.res_read = res_read;
	}
}