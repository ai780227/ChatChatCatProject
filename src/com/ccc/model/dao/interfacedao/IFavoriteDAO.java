package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.FavoriteBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;

public interface IFavoriteDAO {
	public FavoriteBean insert(FavoriteBean fb);
	public FavoriteBean delete(FavoriteBean fb);
	public FavoriteBean getByPrimaryKey(int post_id, String m_id);
	public List<FavoriteBean> getAll();

	public List<FavoriteBean> getFavoritesByMemberBean(MemberBean mb);
	public List<FavoriteBean> getFavoritesByPostBean(PostBean pb);
	public List<FavoriteBean> getFavoritesByMethod(String query);
}
