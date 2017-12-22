package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.ManagerBean;

public interface IManagerDAO {
	public ManagerBean insert(ManagerBean mgr);
	public ManagerBean update(ManagerBean mgr);
	public ManagerBean delete(ManagerBean mgr);
	public ManagerBean getByPrimaryKey(String mgr_id);
	public List<ManagerBean> getAll();
	
	public List<ManagerBean> getManagersByName(String name);
	public List<ManagerBean> getManagersByEmail(String email);
	public List<ManagerBean> getManagersByMethod(String query);	
}
