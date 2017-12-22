package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.MemberBean;

public interface IMemberDAO {
	public MemberBean insert(MemberBean mem);
	public MemberBean update(MemberBean mem);
	public MemberBean delete(MemberBean mem);
	public MemberBean getByPrimaryKey(String m_id);
	public List<MemberBean> getAll();
	
	public List<MemberBean> getMembersByName(String name);
	public List<MemberBean> getMembersByEmail(String email);
	public List<MemberBean> getMembersByMethod(String query);
}
