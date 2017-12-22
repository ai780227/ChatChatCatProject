package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;

public interface IActivityDAO {
	public ActivityBean insert(ActivityBean value);
	public ActivityBean update(ActivityBean value);
	public ActivityBean delete(ActivityBean value);
	public ActivityBean getByPrimaryKey(int value);
	public List<ActivityBean> getAllPublicAct(); // 查詢所有尚未過期公開活動
	
	//皆編輯活動用,抓Public也可以供查詢
	public List<ActivityBean> getPublicActByTheMember(String value); // 查詢該會員發起且尚未過期之公開活動
	public List<ActivityBean> getPrivateActByTheMember(String value); // 查詢該會員發起且尚未過期之私人活動
	public List<ActivityBean> getActByTheMember(MemberBean mem); //查詢該會員發起且尚未過期的所有活動(依時間大小排序)且活動屬性!=2
	public List<String> getMyFriendNotInMyAct(int act_id,MemberBean mem); //查詢尚未加入該會員活動的好友
	
	//瀏覽活動->準備參加的活動用
	public List<ActivityBean> getReadyToGoPublicActs(MemberBean mem); //瀏覽該會員準備參加之尚未過期的公開活動(參加意願為1)
	public List<ActivityBean> getReadyToGoPrivateActs(MemberBean mem); //瀏覽該會員準備參加之尚未過期的私人活動(參加意願為1)
	
	//瀏覽活動->接收的邀請用
	public List<ActivityBean> viewTheInviteOfPri(MemberBean mem);//瀏覽所有尚未過期且該會員已被邀請之私人活動
	public List<ActivityBean> viewTheInviteOfPub(MemberBean mem);//瀏覽所有尚未過期且該會員已被邀請之公開活動
	
	//封鎖活動
	public boolean updateProperty(ActivityBean value);//更新活動屬性為2
	
	//查詢用
//	public List<ActivityBean> getActFromTodayToTheDay(String value); // 查詢從今天到指定日期之所有公開活動
//	public List<ActivityBean> getActLocation(String value); //以關鍵字串查詢活動地點
//	public List<ActivityBean> getActTitle(String value); //以關鍵字串查詢活動標題
	public List<ActivityBean> getActivitiesByString(String value); //結合地點標題的搜尋
	
	public List<ActivityBean> getActivityByMethod(String query); 
}
