package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityNoticeBean;
import com.ccc.model.bean.MemberBean;

public interface IActivityNoticeDAO {
	public ActivityNoticeBean insert(ActivityNoticeBean value);
	public ActivityNoticeBean update(ActivityNoticeBean value);
	public ActivityNoticeBean delete(ActivityNoticeBean value);
	public ActivityNoticeBean getByPrimaryKey(int value);
	public List<ActivityNoticeBean> getAll();	
	
	public ActivityNoticeBean getNotByMemAndAct(MemberBean mem,ActivityBean actIJoin); //透過會員及活動查詢
	public List<ActivityNoticeBean> getMemAllActMsg(MemberBean value);//搜尋該會員所有的通知
	public List<ActivityNoticeBean> getMemActUnreadNotice(MemberBean value);//搜尋該會員所有尚未讀取的通知
	public List<ActivityNoticeBean> getThisActNotices(ActivityBean act);//搜尋該筆活動的所有通知
	public List<ActivityNoticeBean> getActivityNoticeByMethod(String query);
}
