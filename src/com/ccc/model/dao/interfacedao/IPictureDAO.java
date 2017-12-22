package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.PictureBean;

public interface IPictureDAO {
	public PictureBean insert(PictureBean pic);
	public PictureBean update(PictureBean pic);
	public PictureBean delete(PictureBean pic);
	public PictureBean getByPrimaryKey(int pic_id);
	public List<PictureBean> getAll();
	
	public List<PictureBean> getPicturesByM_id(String m_id);
	public List<PictureBean> getPicturesByC_id(int c_id);
	public List<PictureBean> getPicturesByMethod(String query);	

}
