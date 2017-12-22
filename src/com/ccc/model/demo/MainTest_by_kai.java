package com.ccc.model.demo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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



public class MainTest_by_kai {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		MemberDAO daoOfMem = (MemberDAO) context.getBean("MemberDAO");
		ActivityDAO daoOfAct = (ActivityDAO) context.getBean("ActivityDAO");
		ActivityNoticeDAO daoOfNot = (ActivityNoticeDAO) context.getBean("ActivityNoticeDAO");
		ActivityParticipateDAO daoOfPar = (ActivityParticipateDAO) context.getBean("ActivityParticipateDAO");
		
		//建立ActivityService物件
	
		//準備虛擬物件
		MemberBean memP = null;
		ActivityBean actP = null;
		ActivityNoticeBean notP = null;
		ActivityParticipateBean parP = null;
		List<ActivityBean> actsP = null; 
		List<ActivityNoticeBean> notsP = null;
		List<ActivityParticipateBean> parsP = null;
		
		//PrintsDATA Object
		TestActivityDAO printAct = new TestActivityDAO(); 
// ------------------------TestActivityService------------------------
	/*	
		  
		//建立測試物件 
		MemberBean mem = new MemberBean();
		MemberBean mem1 = new MemberBean();
		MemberBean mem2 = new MemberBean();
		MemberBean mem3 = new MemberBean();
				
		ActivityParticipateBean par1 = new ActivityParticipateBean();
		ActivityParticipateBean par2 = new ActivityParticipateBean();
		ActivityParticipateBean par3 = new ActivityParticipateBean();
		
		ActivityNoticeBean not1 = new ActivityNoticeBean();
		ActivityNoticeBean not2 = new ActivityNoticeBean();
		ActivityNoticeBean not3 = new ActivityNoticeBean();
		
//之後活動關係人,以及活動通知通通放在List<Bean>裡面		 
		
		//建立發起活動會員
		mem.setM_id("cecj006");
		
		//建立被邀請之會員
		mem1.setM_id("cecj001");
		mem2.setM_id("cecj002");
		mem3.setM_id("cecj003");
		
		//建立私人活動Bean
		ActivityBean act = new ActivityBean();
		act.setMember(mem);
		act.setAct_property(0);
		act.setAct_title("大家一起來爆走");
		act.setAct_time(new Timestamp(Timestamp.valueOf("2014-04-18 08:00:00").getTime()));
		act.setAct_location("資策會");
		act.setAct_content("我叫你們快點做專題");
		act.setAct_deadline(new Timestamp(Timestamp.valueOf("2014-04-11 08:00:00").getTime()));
		
		//設定活動關係人
	
		par1.setActivity(act);
		par1.setMember(mem1);
		par1.setAct_participate(0);
	
		par2.setActivity(act);
		par2.setMember(mem2);
		par2.setAct_participate(0);
	
		par3.setActivity(act);
		par3.setMember(mem3);
		par3.setAct_participate(0);
		
		parsP.add(par);		
		parsP.add(par1);
		parsP.add(par2);
		parsP.add(par3);
		
		//設定活動通知		
		not1.setMember(mem1);
		not1.setActivity(act);
		not1.setAct_read(0);
	
		not2.setMember(mem2);
		not2.setActivity(act);
		not2.setAct_read(0);
	
		not3.setMember(mem3);
		not3.setActivity(act);
		not3.setAct_read(0);
		
		notsP.add(not1);
		notsP.add(not2);
		notsP.add(not3);
		
		
		//新增活動測試		
		actService.createAct(act);
		actService.createPars(par);
		actService.createPars(par1);
		actService.createPars(par2);
		actService.createPars(par3);
		actService.createNots(not1);
		actService.createNots(not2);
		actService.createNots(not3);
		
		//刪除活動測試
		actService.deleteAct(daoOfAct.getByPrimaryKey(6));
		
	
		//編輯活動測試
		actP= daoOfAct.getByPrimaryKey(9);
		actP.setAct_title("秀特勃起了");
		actService.editAct(actP);
	
		
		//瀏覽所有尚未過期之公開活動
		actsP = actService.viewICanJoin();
		printAct.printList(actsP);

			
		
		//瀏覽該會員歷史活動
		MemberBean mem = new MemberBean();
		mem.setM_id("cecj001");	
		try {
			printAct.printList(actService.viewHistoryActs(mem));
		} catch (Exception e) {
			System.out.println("查無資料");
		} 
	    
		//該會員準備參加之尚未過期的公開活動(參加意願為1)
		actP = daoOfAct.getByPrimaryKey(8);
		actService.joinAct(daoOfMem.getByPrimaryKey("cecj006"),actP);

		//退出活動
		actP = daoOfAct.getByPrimaryKey(1);
		memP = daoOfMem.getByPrimaryKey("cecj003");
		actService.quitAct(memP, actP);

		//更改活動關係為不確定
		actP = daoOfAct.getByPrimaryKey(2);
		memP = daoOfMem.getByPrimaryKey("cecj004");
		actService.notSureAct(memP, actP);

		//瀏覽該會員準備參加之尚未過期的(公開/私人)活動(參加意願為1)
		printAct.printList(daoOfAct.getReadyToGoPublicActs(daoOfMem.getByPrimaryKey("cecj006")));
		printAct.printList(daoOfAct.getReadyToGoPrivateActs(daoOfMem.getByPrimaryKey("cecj006")));
*/
		//瀏覽該會員已被邀請且尚未過期之(公開/私人)活動(參加意願為0)
		printAct.printList(daoOfAct.viewTheInviteOfPri(daoOfMem.getByPrimaryKey("cecj006")));
		printAct.printList(daoOfAct.viewTheInviteOfPub(daoOfMem.getByPrimaryKey("cecj006")));
		
//		Session session = DefaultFactory.getSessionFactory().openSession();
// ------------------------TestActivityDAO------------------------
/*		ActivityDAO dao = (ActivityDAO) context.getBean("ActivityDAO");
		MemberBean user = new MemberBean(); 
		ActivityBean act = new ActivityBean();
		
		user.setM_id("cecj001");
		
//		act.setMember(user);
//		act.setAct_property(1);
//		act.setAct_title("大貓會小貓");
//		act.setAct_time(new Timestamp(Timestamp.valueOf("2014-03-31 08:00:00").getTime()));
//		act.setAct_location("捷運動物園站");
//		act.setAct_content("大大貓貓會小小貓貓");
//		act.setAct_deadline(new Timestamp(Timestamp.valueOf("2014-03-26 08:00:00").getTime()));
		
		try {		
			TestActivityDAO print = new TestActivityDAO();
			
//			dao.insert(act);
			
//			act = dao.getByPrimaryKey(4);
//			act.setAct_title("大貓咬小貓");
//		    dao.update(act);
			
//			dao.delete(act);
	
//			print.printList(dao.getAllPublicAct());
			
//			print.printList(dao.getPublicActByTheMember("cecj003"));
			
//			print.printList(dao.getPrivateActByTheMember("cecj004"));
			
			//print.printList(dao.getReadyToGoPublicActs("cecj002"));

//			print.printList(dao.getActFromTodayToTheDay("2014-04-01"));
			
//			print.printList(dao.getLocation("捷運"));
			
//			print.printList(dao.getTitle("小野貓"));
			
//			print.printParm(act);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 

*/	
// ------------------------TestActivityNoticeDAO------------------------
/*		ActivityNoticeDAO dao = (ActivityNoticeDAO) context.getBean("ActivityNoticeDAO");
		ActivityNoticeBean not = new ActivityNoticeBean(); 
		MemberBean user = new MemberBean();
		ActivityBean act = new ActivityBean();
		
		user.setM_id("cecj003");
		act.setAct_id(3);
		
		not.setAct_notid(7);;
		not.setActivity(act);
		not.setMember(user);
		not.setAct_read(1);
		not.setAct_time(new Timestamp(System.currentTimeMillis()));
		
		try {
			TestActivityNoticeDAO print = new TestActivityNoticeDAO();
			
//			dao.insert(not);
			
//			not =dao.getByPrimaryKey(5);
//			not.setAct_read(0);
//			dao.update(not);
			
//			not = dao.getByPrimaryKey(6);
//			dao.delete(not);
			
//			print.printList(dao.getAll());
			
//			print.printList(dao.getMemActUnreadNotice(user));
			
//			print.printList(dao.getMemAllActMsg(user));
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
*/
		
	
// ------------------------TestActivityParticipateDAO------------------------
/*		ActivityParticipateDAO dao = (ActivityParticipateDAO) context.getBean("ActivityParticipateDAO");
		
		TestActivityParticipateDAO print = new TestActivityParticipateDAO();
		ActivityParticipateBean par = new ActivityParticipateBean();
		MemberBean user = new MemberBean();
		ActivityBean act = new ActivityBean();
		user.setM_id("cecj003");
//		act.setAct_id(3);
		try {
//			par.setActivity(act);;
//			par.setMember(user);
//			par.setAct_participate(0);
			
//			dao.insert(par);
			
//			par=dao.getByPrimaryKey(act,user);
//		    par.setAct_participate(1);
//			dao.update(par);
			
//			par=dao.getByPrimaryKey(3,"cecj002");
//			if(par!=null)
//				print.printParm(par);
//			else
//				System.out.println("不存在");
//			dao.delete(par);
			
//			print.printList(dao.getAll());
			print.printList(dao.getMemWasInvited(user));
//			print.printList(dao.getActRelated(act));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
*/	
				
		System.out.println("main:done.");

	}

}
