package com.ccc.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ReportBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.ReportDAO;

public class ReportService {
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
	PostDAO postdao=(PostDAO) context.getBean("PostDAO");// 建立PostDAO物件
	ReportDAO reportdao= (ReportDAO) context.getBean("ReportDAO");// 建立ReportDAO物件
	MemberDAO memberdao = (MemberDAO) context.getBean("MemberDAO");// 建立MemberDAO物件


	//封鎖貼文服務

	// 拿到update回傳的PostBean物件
	public PostBean LockPost(PostBean post) {// 代入PostBean物件
		if(post!=null){
		PostBean po = postdao.update(post);// 將PostBean物件代入update,回傳PostBean物件
		return po;// 回傳PostBean物件，給WarningNotice使用
		}else{
			return null;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////

	// 瀏覽所有檢舉貼文服務
	
	//拿到List<ReportBean>物件
	public List<ReportBean> viewAllReportPost() {
		List<ReportBean> reportlist;
		reportlist = reportdao.getAll();//呼叫getAll,回傳List<ReportBean>物件
		return reportlist;//回傳List<ReportBean>物件
	}
	
	//拿到被檢舉的貼文的List<PostBean>物件
	public List<PostBean> getAllReportPost(List<ReportBean> reportlist) { // 代入List<ReportBean>物件，回傳List<PostBean>物件
		if(reportlist!=null){
		List<PostBean> postlist = new ArrayList<PostBean>();//new一個List<PostBean>物件
		for (ReportBean report : reportlist) {//從List物件一個個取出給ReportBean物件
			int post_id = report.getPost().getPost_id();//回傳PostID
			PostBean post = postdao.getByPrimaryKey(post_id);//回傳PostBean物件
			postlist.add(post);//加入一個PostBean物件放入List
		}
		return postlist;
		}else{
			return null;
		}
	}
	
	
	public List<MemberBean> getReportByPeople(List<ReportBean> reportlist) {// 代入List<ReportBean>物件，回傳List<MemeberBean>物件
		if(reportlist!=null){
		List<MemberBean> memberlist = new ArrayList<MemberBean>();//new一個List<MemberBean>物件
		for (ReportBean report : reportlist) {//從List物件一個個取出給MemberBean物件
			String M_id = report.getMember().getM_id();//回傳M_ID
			MemberBean member = memberdao.getByPrimaryKey(M_id);//回傳MemberBean物件
			memberlist.add(member);//加入一個MemberBean物件放入List
		}
		return memberlist;
		}else{
			return null;
		}
	}

	//////////////////////////////////////////////////////////////////////////
	
	// 瀏覽一則檢舉貼文服務
	
	public PostBean getOneReportPost(ReportBean reportpost) { // 代入ReportBean物件，回傳PostBean物件
		if(reportpost!=null){
		PostDAO postdao=(PostDAO) context.getBean("PostDAO");// 建立PostDAO物件
		int post_id = reportpost.getPost().getPost_id();//回傳PostID
		PostBean post = postdao.getByPrimaryKey(post_id);//代入PostID,回傳PostBean物件
		return post;//回傳PostBean物件
		}else{
			return null;
		}
	}
	
	//拿到檢舉人的MemberBean物件
	public MemberBean getReportByPerson(ReportBean reportpost) {// 代入ReportBean物件，回傳MemeberBean物件
		if(reportpost!=null){
		MemberDAO memberdao = (MemberDAO) context.getBean("MemberDAO");
		String M_id = reportpost.getMember().getM_id();//回傳M_id
		MemberBean member= memberdao.getByPrimaryKey(M_id);//代入M_id,回傳MemberBean物件
		return member;//回傳MemberBean物件
		}else{
			return null;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	//新增檢舉貼文
	public ReportBean insertReport(ReportBean rep){
		if(rep!=null){
			ReportDAO reportdao = (ReportDAO) context.getBean("ReportDAO");
			ReportBean re=reportdao.insert(rep);
			return re;
		}else{
			return null;
		}
		
	}
	
	///////////////////////////////////////////////////////////////
	
	//刪除檢舉貼文
	public List<ReportBean> deleteReports(PostBean post){
		if(post!=null){
			ReportDAO reportdao = (ReportDAO) context.getBean("ReportDAO");
			List<ReportBean> replist=reportdao.deleteByPostID(post);
			return replist;
		}else{
			return null;
		}
		
	}
	
	////////////////////////////////////////////////////////////////
	
	//更改會員的違規次數
	public int updateViolationCount(String m_id){
		MemberDAO memdao=(MemberDAO)context.getBean("MemberDAO");
		MemberBean mem=memdao.getByPrimaryKey(m_id);
		int count=mem.getM_violation_count();
		count++;
		mem.setM_violation_count(count);
		MemberBean member=memdao.update(mem);
		int times=member.getM_violation_count();
		return times;
	}
	
	//當違規次數達到3次時，把會員權限封鎖
	public void LockAccount(String m_id){
		MemberDAO memdao=(MemberDAO)context.getBean("MemberDAO");
		MemberBean mem=memdao.getByPrimaryKey(m_id);
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		mem.setM_block_time(ts);
		mem.setM_authority(2);
		memdao.update(mem);
	}
	
	public static void main(String[] arg0) {
		ReportService p = new ReportService();

		// 測試封鎖貼文
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
//		PostDAO postdao=(PostDAO) context.getBean("PostDAO");
//		PostBean post=postdao.getByPrimaryKey(1);
//		post.setPost_property(2);
//		 p.LockPost(post);

		// 測試瀏覽所有檢舉貼文
		 List<ReportBean> reportlist=p.viewAllReportPost();
		 List<PostBean> postlist=p.getAllReportPost(reportlist);
		 List<MemberBean> memberlist=p.getReportByPeople(reportlist);
		 for(int i=0,j=0,k=0;i<reportlist.size() && j<postlist.size() &&
		 k<memberlist.size();i++,j++,k++){
		 System.out.println(postlist.get(j).getMemberBean().getM_name());//被檢舉人姓名
		 System.out.println(postlist.get(j).getPost_content());//被檢舉人的貼文內容
		 System.out.println(postlist.get(j).getPost_time());//貼文時間
		 System.out.println(reportlist.get(i).getRep_cause());// 檢舉原因
		 System.out.println(memberlist.get(k).getM_name());//檢舉人姓名
		 }

		// 測試瀏覽一則檢舉貼文
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
//		ReportDAO reportdao= (ReportDAO) context.getBean("ReportDAO");
//		ReportBean reportpost=reportdao.getByPrimaryKey(5);
//		
//		PostBean post = p.getOneReportPost(reportpost);
//		MemberBean member = p.getReportByPerson(reportpost);
//		System.out.println(post.getMemberBean().getM_name());// 被檢舉人姓名
//		System.out.println(post.getPost_content());// 被檢舉人的貼文內容
//		System.out.println(post.getPost_time());// 貼文時間
//		System.out.println(reportpost.getRep_cause());// 檢舉原因
//		System.out.println(member.getM_name());// 檢舉人姓名
	}
}