package com.ccc.model.dao.interfacedao;

import java.util.List;





import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.NewsBean;
import com.ccc.model.bean.PostBean;

public interface INewsDAO {
	public NewsBean insert(NewsBean newsB);
	public NewsBean update(NewsBean newsB);
	public NewsBean delete(NewsBean newsB);
	public NewsBean getByPrimaryKey(int newsid);
	public List<NewsBean> getAll();
	
	public List<NewsBean> getByMgrId(String value);
	public List<NewsBean> getByTitle(String value);
	public List<NewsBean> getByDayToDay(String value1, String value2);
	public List<NewsBean> getByNewsContent(String value);
	public List<NewsBean> getByNewsSource(String value);
	public List<NewsBean> getNewsByMethod(String query);
	public void queryByNewsBean(NewsBean nb);
}
