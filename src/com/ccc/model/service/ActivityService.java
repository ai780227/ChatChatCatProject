package com.ccc.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityNoticeBean;
import com.ccc.model.bean.ActivityParticipateBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.ActivityDAO;
import com.ccc.model.dao.ActivityNoticeDAO;
import com.ccc.model.dao.ActivityParticipateDAO;
import com.ccc.model.dao.MemberDAO;

public class ActivityService {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"beans.config.xml");
	ActivityDAO actDAO = (ActivityDAO) context.getBean("ActivityDAO");
	ActivityNoticeDAO notDAO = (ActivityNoticeDAO) context
			.getBean("ActivityNoticeDAO");
	ActivityParticipateDAO parDAO = (ActivityParticipateDAO) context
			.getBean("ActivityParticipateDAO");
	MemberDAO memDAO = (MemberDAO) context.getBean("MemberDAO");

	MemberBean mem = null;
	ActivityBean act = new ActivityBean();
	ActivityNoticeBean not = new ActivityNoticeBean();
	ActivityParticipateBean par = new ActivityParticipateBean();

	List<ActivityBean> acts = new ArrayList<ActivityBean>();
	List<ActivityParticipateBean> pars = new ArrayList<ActivityParticipateBean>();
	List<ActivityNoticeBean> nots = new ArrayList<ActivityNoticeBean>();

	// 建立活動
	public ActivityBean createAct(ActivityBean act) {
		try {
			return actDAO.insert(act);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("新增活動失敗");
			return null;
		}
	}

	/*
	 * // 新增活動通知 public ActivityNoticeBean createNots(ActivityNoticeBean nots) {
	 * try { notDAO.insert(nots); System.out.println("新增活動通知成功"); return nots; }
	 * catch (Exception e) { e.printStackTrace();
	 * System.out.println("新增活動通知失敗"); return null; } }
	 * 
	 * 
	 * // 新增活動關係人 public ActivityParticipateBean
	 * createPars(ActivityParticipateBean pars) { try { parDAO.insert(pars);
	 * System.out.println("新增活動關係人成功"); return pars; } catch (Exception e) {
	 * e.printStackTrace(); System.out.println("新增活動關係人失敗"); return null; } }
	 */
	// createNots用List<String>來新增資料
	public boolean createNots(List<String> mems, ActivityBean act) {
		if (mems.isEmpty() == false) {
			Iterator<String> data = mems.iterator();
			while (data.hasNext()) {
				mem = memDAO.getByPrimaryKey(data.next());
				not.setMember(mem);
				not.setActivity(act);
				not.setAct_read(0);
				notDAO.insert(not);
			}
			System.out.println("新增活動通知成功");
			return true;
		} else {
			System.out.println("無影響任何活動通知");
			return false;
		}
	}

	// createPars用List<String>來新增資料
	public boolean createPars(List<String> mems, ActivityBean act) {
		if (mems.isEmpty() == false) {
			Iterator<String> data = mems.iterator();
			while (data.hasNext()) {
				mem = memDAO.getByPrimaryKey(data.next());
				par.setMember(mem);
				par.setActivity(act);
				par.setAct_participate(0);
				parDAO.insert(par);
			}
			System.out.println("新增活動關係人成功");
			return true;
		} else {
			System.out.println("無影響任何活動關係人");
			return false;
		}
	}

	// 刪除活動 (更改狀態非刪除該筆資料)
	public boolean deleteAct(ActivityBean act) {
		try {
			// 更新該筆活動的屬性為2(封鎖該活動)
			act.setAct_property(2);
			actDAO.updateProperty(act);
			// 搜尋此活動的活動關係人
			List<ActivityParticipateBean> pars = parDAO.getActRelated(act);
			Iterator<ActivityParticipateBean> itP = pars.iterator();
			while (itP.hasNext()) {
				// 刪除關係人
				parDAO.delete(itP.next());
			}
			// 搜尋該筆活動的所有通知
			List<ActivityNoticeBean> nots = notDAO.getThisActNotices(act);
			Iterator<ActivityNoticeBean> itN = nots.iterator();
			while (itN.hasNext()) {
				// 刪除活動通知
				notDAO.delete(itN.next());
			}
			System.out.println("封鎖活動成功");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("刪除活動失敗");
			return false;
		}
	}

	// 編輯活動
	public ActivityBean editAct(ActivityBean act) {
		// 可供更改的參數 act_title act_time act_location act_content
		try {
			actDAO.update(act);
			System.out.println("更新活動資訊成功");
			return act;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("更新活動資訊失敗");
			return null;
		}
	}

	// 瀏覽所有尚未過期之公開活動
	public List<ActivityBean> viewICanJoin() {
		acts = actDAO.getAllPublicAct();
		if (acts != null) {
			return acts;
		} else {
			return null;
		}
	}

	// 瀏覽該會員準備參加之尚未過期的公開活動(參加意願為1)
	public List<ActivityBean> viewReadyToGoPublicActs(MemberBean mem) {
		return actDAO.getReadyToGoPublicActs(mem);
	}

	// 瀏覽該會員歷史活動
	public List<ActivityBean> viewHistoryActs(MemberBean mem) {
		Integer act_id = null;
		pars = parDAO.getMemWasInvited(mem);
		try {
			Iterator<ActivityParticipateBean> data = pars.iterator();
			while (data.hasNext()) {
				act_id = data.next().getActivity().getAct_id();
				act = actDAO.getByPrimaryKey(act_id);
				acts.add(act);
			}
			return acts;
		} catch (Exception e) {
			System.out.println("查無資料");
			return acts;
		}
	}

	// 瀏覽該會員準備參加之尚未過期的私人活動(參加意願為1)
	public List<ActivityBean> viewReadyToGoPrivateActs(MemberBean mem) {
		return actDAO.getReadyToGoPrivateActs(mem);
	}

	// 該會員報名參加公開活動(參加意願為1:接受)
	public boolean joinAct(MemberBean mem, ActivityBean actIJoin) {
		// 設定自己為活動關係人
		par.setActivity(actIJoin);
		par.setMember(mem);
		par.setAct_participate(1);
		// 設定自己的活動通知
		not.setMember(mem);
		not.setActivity(actIJoin);
		not.setAct_read(1);

		try {
			parDAO.update(par);
			notDAO.update(not);
			System.out.println("參加成功");
			return true;
		} catch (Exception e) {
			System.out.println("參加失敗");
			return false;
		}
	}

	// 退出活動(參加意願為2:拒絕)，並刪除活動通知
	public boolean quitAct(MemberBean mem, ActivityBean actIJoin) {
		par = parDAO.getByPrimaryKey(actIJoin.getAct_id(), mem.getM_id());
		par.setAct_participate(2);
		not = notDAO.getNotByMemAndAct(mem, actIJoin);
		try {
			parDAO.update(par);
			notDAO.delete(not);
			System.out.println("退出活動成功");
			return true;
		} catch (Exception e) {
			System.out.println("退出活動失敗");
			return false;
		}
	}

	// 更改不確定會參加活動(參加意願為3：不確定)
	public boolean notSureAct(MemberBean mem, ActivityBean actIJoin) {
		par = parDAO.getByPrimaryKey(actIJoin.getAct_id(), mem.getM_id());
		par.setAct_participate(3);
		try {
			parDAO.update(par);
			System.out.println("更新活動關係為不確定");
			return true;
		} catch (Exception e) {
			System.out.println("更新失敗");
			return false;
		}
	}

	// 抓活動主鍵
	public ActivityBean getByPrimaryKeyAct(int value) {
		return actDAO.getByPrimaryKey(value);
	}

	// 抓活動通知主鍵
	public ActivityNoticeBean getByPrimaryKeyNot(int value) {
		return notDAO.getByPrimaryKey(value);
	}

	// 抓活動關係人主鍵
	public ActivityParticipateBean getByPrimaryKeyPar(int actId, String mId) {
		return parDAO.getByPrimaryKey(actId, mId);
	}

	// 查詢該會員發起且尚未過期之公開活動
	public List<ActivityBean> viewPublicActByTheMember(String value) {
		return actDAO.getPublicActByTheMember(value);
	}

	// 查詢該會員發起且尚未過期之私人活動
	public List<ActivityBean> viewPrivateActByTheMember(String value) {
		return actDAO.getPrivateActByTheMember(value);
	}

/*	// 查詢從今天到指定日期之所有公開活動
	public List<ActivityBean> viewActFromTodayToTheDay(String value) {
		return actDAO.getActFromTodayToTheDay(value);
	}

	// 以關鍵字串查詢活動地點
	public List<ActivityBean> viewActLocation(String value) {
		return actDAO.getActLocation(value);
	}

	// 以關鍵字串查詢活動標題
	public List<ActivityBean> viewActTitle(String value) {
		return actDAO.getActTitle(value);
	}
*/
	// 瀏覽所有尚未過期且該會員已被邀請之私人活動
	public List<ActivityBean> viewTheInviteOfPri(MemberBean mem) {
		return actDAO.viewTheInviteOfPri(mem);
	}

	// 瀏覽所有尚未過期且該會員已被邀請之公開活動
	public List<ActivityBean> viewTheInviteOfPub(MemberBean mem) {
		return actDAO.viewTheInviteOfPub(mem);
	}

	// 透過會員及活動查詢活動通知
	public ActivityNoticeBean viewNotByMemAndAct(MemberBean mem,
			ActivityBean actIJoin) {
		return notDAO.getNotByMemAndAct(mem, actIJoin);
	}

	// 搜尋該會員所有的通知
	public List<ActivityNoticeBean> viewMemAllActMsg(MemberBean value) {
		return notDAO.getMemAllActMsg(value);
	}

	// 搜尋該會員所有尚未讀取的通知
	public List<ActivityNoticeBean> viewMemActUnreadNotice(MemberBean value) {
		return notDAO.getMemActUnreadNotice(value);
	}

	// 搜尋此活動的活動關係人
	public List<ActivityParticipateBean> viewActRelated(ActivityBean value) {
		return parDAO.getActRelated(value);
	}

	// 該會員的歷史活動
	public List<ActivityParticipateBean> viewMemWasInvited(MemberBean value) {
		return parDAO.getMemWasInvited(value);
	}

	// 查詢該會員發起且尚未過期的所有活動(依時間大小排序)
	public List<ActivityBean> viewActByTheMember(MemberBean mem) {
		return actDAO.getActByTheMember(mem);
	}

	// 查詢尚未加入該會員活動的好友
	public List<MemberBean> viewMyFriendNotInMyAct(int act_id, MemberBean mem) {
		List<String> myFri = actDAO.getMyFriendNotInMyAct(act_id, mem);
		List<MemberBean> myFriNotInMyAct = new ArrayList<MemberBean>();
		Iterator<String> it = myFri.iterator();
		while (it.hasNext()) {
			myFriNotInMyAct.add(memDAO.getByPrimaryKey(it.next()));
		}
		return myFriNotInMyAct;
	}

	// 回傳該活動有多少人參加
	public Integer viewCountOfTheActWhoJoin(ActivityBean act) {
		return parDAO.getCountOfTheActWhoJoin(act);
	}
	
	//查詢該會員可以參加的所有公開活動
	public List<ActivityBean> viewICanJoinPubAct(MemberBean mem){
		//瀏覽所有尚未過期之公開活動
		List<ActivityBean> pubActs = viewICanJoin();
		Iterator<ActivityBean> itA = pubActs.iterator();
		//新增一份放置瀏覽所有尚未過期之公開活動act_id的集合
		List<Integer> pubActsID = new ArrayList<Integer>();
		while(itA.hasNext()){
			pubActsID.add(itA.next().getAct_id());
		}
		
		//複製一份所有尚未過期之公開活動
		List<Integer> actsID = pubActsID;
			
		//瀏覽該會員準備參加之尚未過期的公開活動
		List<ActivityBean> myActs = viewReadyToGoPublicActs(mem);
		Iterator<ActivityBean> itB = myActs.iterator();
		//新增一份瀏覽該會員準備參加之尚未過期的公開活動act_id的集合
		List<Integer> myActsID = new ArrayList<Integer>();
		while(itB.hasNext()){
			myActsID.add(itB.next().getAct_id());
		}
			
		try {
			//如果ID一樣則移出複製的List<Integer>
			for(int i = 0;i< pubActsID.size();i++){
				for(int j = 0;j< myActsID.size();j++){
					if(pubActsID.get(i).equals(myActsID.get(j))){
						actsID.remove(i);
					}
				}
			}
			
			//使用actDAO查找複製後的結果List<ActivityBean>
			List<ActivityBean> newacts = new ArrayList<ActivityBean>();
			Iterator<Integer> it = actsID.iterator();
			while(it.hasNext()){
				newacts.add(actDAO.getByPrimaryKey(it.next()));
			}
			return newacts;
		} catch (Exception e) {
			return pubActs;
		}
		
	}
	
	//結合地點標題的搜尋
	public List<ActivityBean> viewActivitiesByString(String value){
		if(value==""){
			return null;
		}else{
			return actDAO.getActivitiesByString(value);
		}	
	}
	
	//搜尋過後可以參加的活動
	public List<ActivityBean> viewAfterSearchICanJoin(List<ActivityBean> vrg,List<ActivityBean> as){
		//after search Act_id
		List<Integer> asAct_ids = new ArrayList<Integer>();
		Iterator<ActivityBean> itA = as.iterator();
		while(itA.hasNext()){
			asAct_ids.add(itA.next().getAct_id());
		}
		
		//copy after search Act_id
		List<Integer> copyasAct_ids = asAct_ids;
				
		//viewReadyToGoPublicActs Act_id
		List<Integer> vrgAct_ids = new ArrayList<Integer>();
		Iterator<ActivityBean> itB = vrg.iterator();
		while(itB.hasNext()){
			vrgAct_ids.add(itB.next().getAct_id());
		}
		
		try {
				//如果ID一樣則移出複製的List<Integer>
				for(int i = 0;i< asAct_ids.size();i++){
					for(int j = 0;j< vrgAct_ids.size();j++){
						if(asAct_ids.get(i).equals(vrgAct_ids.get(j))){
							copyasAct_ids.remove(i);
						}
					}
				}
				//使用actDAO查找複製後的結果List<ActivityBean>
				Iterator<Integer> it = copyasAct_ids.iterator();
				List<ActivityBean> newacts = new ArrayList<ActivityBean>();
				while(it.hasNext()){
					newacts.add(actDAO.getByPrimaryKey(it.next()));
				}
				return newacts;
		} catch (Exception e) {
			return as;
		}
	}
	
	//判斷該會員是否有參加該活動
	public boolean isYou(MemberBean mem,ActivityBean act){
		boolean switsh = true;
		List<ActivityParticipateBean> pars = viewActRelated(act);
		Iterator<ActivityParticipateBean> it = pars.iterator();
		while(it.hasNext()){			
			ActivityParticipateBean par =	it.next();
			if(par.getMember().getM_id().equals(mem.getM_id()) && par.getAct_participate()==1){
				switsh = false;
				System.out.println("the same people");
			}
		}
		return switsh;
	}
}
