package com.ccc.model.demo;


import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ReportBean;
import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.ReportDAO;
import com.ccc.model.dao.WarningNoticeDAO;


public class MainTest_by_shooter {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//HistoryPost
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		WarningNoticeDAO dao = (WarningNoticeDAO) context.getBean("WarningNoticeDAO");
		WarningNoticeBean war=new WarningNoticeBean();
		MemberBean mem=new MemberBean();
		ActivityBean act=new ActivityBean();
		Iterator ite = null;
		
		//加入一筆資料
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		mem.setM_id("cecj003");
		act.setAct_id(1);
		
		war.setPost_type(0);
		war.setLike_count(3);
		war.setMember(mem);
		war.setPost_content("============");
		war.setPost_property(0);
		war.setPost_time(ts);
		war.setRep_cause("-----------");
		war.setActivity(act);
		
//		dao.insert(war);
		
		//查詢某會員的歷史貼文	
		mem.setM_id("cecj002");
		List<WarningNoticeBean> wars=dao.getByMember(mem);
		ite =wars.iterator();
		while(ite.hasNext()){
			WarningNoticeBean wa = (WarningNoticeBean)ite.next();
			System.out.println(wa.getMember().getM_id());
			System.out.println(wa.getLike_count());
			System.out.println(wa.getPost_content());
			System.out.println(wa.getPost_property());
			System.out.println(wa.getRep_cause());
			System.out.println(wa.getPost_time());
			System.out.println(wa.getWar_notid());
			System.out.println(wa.getWar_read());
			System.out.println(wa.getActivity().getAct_id());
		}
	
		
		
		//刪除一筆資料
		WarningNoticeBean war1=new WarningNoticeBean();
//		war1.setWar_notid(6);
//		war1 = dao.getByPrimaryKey(6);
//		if(war1!=null)
//			dao.delete(war1);
//		else
//			System.out.println("war is not exist");
		
		
		//刪除某人所有的歷史貼文 
		MemberBean mem1=new MemberBean();
		mem1.setM_id("cecj004");
//		dao.deleteByMember(mem1);
		
		
		
		
		//透過PrimaryKey查詢
		WarningNoticeBean war3=dao.getByPrimaryKey(2);
		System.out.println(war3.getMember().getM_id());
		System.out.println(war3.getLike_count());
		System.out.println(war3.getPost_content());
		System.out.println(war3.getPost_property());
		System.out.println(war3.getRep_cause());
		System.out.println(war3.getPost_time());
		System.out.println(war3.getWar_notid());
		System.out.println(war3.getWar_read());
		
		//getAll	
		wars=dao.getAll();
		Iterator list=wars.iterator();
		while (list.hasNext()) {
			WarningNoticeBean ar = (WarningNoticeBean) list.next();
			System.out.println(ar.getMember().getM_id());
			System.out.println(ar.getLike_count());
			System.out.println(ar.getPost_content());
			System.out.println(ar.getPost_property());
			System.out.println(ar.getRep_cause());
			System.out.println(ar.getPost_time());
			System.out.println(ar.getWar_notid());
			System.out.println(ar.getWar_read());
			}

		
		//更新一筆資料 (更新會員)	
		
		MemberBean mem2=new MemberBean();
		war = dao.getByPrimaryKey(3);
		mem2.setM_id("cecj004");
		war.setMember(mem2);		
//		dao.update(war);
		
		//更新一筆資料 (更新時間) 
		war=new WarningNoticeBean();
		
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		war=dao.getByPrimaryKey(1);
		war.setPost_time(ts1);
//		dao.update(war);
		


		
		//Report
		
		//更新一筆資料  
/*		System.out.println("更新一筆資料 ");
		ReportDAO reportdao=(ReportDAO)context.getBean("ReportDAO");
		ReportBean rep=new ReportBean();
		List<ReportBean> reporsts = reportdao.getAll();
		PostBean post=new PostBean();
		
		rep=reportdao.getByPrimaryKey(3);
		mem=new MemberBean();
		mem.setM_id("cecj004");
		rep.setMember(mem);
		
//		reportdao.update(rep);
		
		//透過primaryKey查詢
		System.out.println("透過primaryKey查詢");
		ReportBean report=reportdao.getByPrimaryKey(3);
		System.out.println(report.getMember().getM_id());
		System.out.println(report.getRep_id());
		System.out.println(report.getRep_cause());
		System.out.println(report.getPost().getPost_id());
		
		//新增一筆資料
		System.out.println("新增一筆資料");
		rep = new ReportBean();		
		mem=new MemberBean();
		mem.setM_id("cecj003");
		rep.setMember(mem);		
		post.setPost_id(3);
		rep.setPost(post);		
		rep.setRep_cause("不要寫色色的東西");		
//		reportdao.insert(rep);
		
		
		//getAll
		System.out.println("getAll");
		ite = reporsts.iterator();
		while(ite.hasNext()){
			ReportBean re=(ReportBean)ite.next();
			System.out.println(re.getRep_cause());
			System.out.println(re.getRep_id());
			System.out.println(re.getMember().getM_id());
			System.out.println(re.getPost().getPost_id());
		}
		
		//刪除一筆資料
		System.out.println("刪除一筆資料");
		rep=new ReportBean();
		rep = reportdao.getByPrimaryKey(4);
		if(rep!=null)
			reportdao.delete(rep);
		else
			System.out.println("---report is not exist---");
//		rep.setRep_id(4);
//		reportdao.delete(rep);
		
		
		//刪除某貼文的檢舉
		System.out.println("刪除某貼文的檢舉");
		post=new PostBean();
		post.setPost_id(2);
//		reportdao.deleteByPostID(post);
		
		
		//透過會員id刪除檢舉
		System.out.println("透過會員id刪除檢舉");
		mem=new MemberBean();
		mem.setM_id("cecj001");
//		reportdao.deleteByMemberID(mem);
		
		
		//透過會員id查詢
		System.out.println("透過會員id查詢");
		mem=new MemberBean();
		mem.setM_id("cecj001");
		reporsts = reportdao.getByMember(mem);
		Iterator people =reporsts.iterator();
		while (people.hasNext()) {
			ReportBean re = (ReportBean) people.next();
			System.out.println(re.getMember().getM_id());
			System.out.println(re.getRep_id());
			System.out.println(re.getRep_cause());
			System.out.println(re.getPost().getPost_id());
		}
		
*/		
	}

}
