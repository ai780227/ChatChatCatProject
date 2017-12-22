package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityParticipateBean;
import com.ccc.model.bean.MemberBean;

public interface IActivityParticipateDAO {
	
	public ActivityParticipateBean insert(ActivityParticipateBean value);
	public ActivityParticipateBean update(ActivityParticipateBean value);
	public ActivityParticipateBean delete(ActivityParticipateBean act);
	public ActivityParticipateBean getByPrimaryKey(int actId, String mId);
	public List<ActivityParticipateBean> getAll();
	
	public List<ActivityParticipateBean> getActRelated(ActivityBean value);
	public List<ActivityParticipateBean> getMemWasInvited(MemberBean value);
	public Integer getCountOfTheActWhoJoin(ActivityBean act);//回傳該活動有多少人參加
	public List<ActivityParticipateBean> getActivityParticipateByMethod(String query);
}

