package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ResponseBean;

public interface IResponseDAO {
	public ResponseBean insert(ResponseBean resb);
	public ResponseBean update(ResponseBean resb);
	public ResponseBean delete(ResponseBean resb);
	public ResponseBean getByPrimaryKey(int res_id);
	public List<ResponseBean> getAll();

	public List<ResponseBean> getResponsesByMemberBean(MemberBean mb);
	public List<ResponseBean> getResponsesByPostBean(PostBean pb);
	public List<ResponseBean> getResponsesByMethod(String query);
}
