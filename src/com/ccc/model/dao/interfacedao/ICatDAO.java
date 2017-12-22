package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.CatBean;

public interface ICatDAO {
	public CatBean insert(CatBean cat);
	public CatBean update(CatBean cat);
	public CatBean delete(CatBean cat);
	public CatBean getByPrimaryKey(Integer c_id);
	public List<CatBean> getAll();
	
	public List<CatBean> getCatsByM_id(String m_id);
	public List<CatBean> getCatsByMethod(String query);	

}
