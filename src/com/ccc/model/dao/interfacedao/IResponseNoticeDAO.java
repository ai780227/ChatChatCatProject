package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.ResponseNoticeBean;

public interface IResponseNoticeDAO {
	public ResponseNoticeBean insert(ResponseNoticeBean rnb);
	public ResponseNoticeBean update(ResponseNoticeBean rnb);
	public ResponseNoticeBean delete(ResponseNoticeBean rnb);
	public ResponseNoticeBean getByPrimaryKey(int res_notid);
	public List<ResponseNoticeBean> getAll();

	public List<ResponseNoticeBean> getResponseNoticeByM_id_to(String m_id_to);
	public List<ResponseNoticeBean> getResponseNoticeByM_id_from(String m_id_from);
	public List<ResponseNoticeBean> getResponseNoticeByRes_read(int res_read);
	public List<ResponseNoticeBean> getResponseNoticeByMethod(String query);
	public List<ResponseNoticeBean> getResponseNoticeByResponseBean(ResponseBean resb);
}
