package com.ccc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ReportBean;
import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.service.NoticeService;
import com.ccc.model.service.ReportService;

/**
 * Servlet implementation class ViewNoticeServlet
 */

public class InsertWarningNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertWarningNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//將瀏覽器傳過來的要求轉成UTF-8
		
		String War_mId=request.getParameter("ReportedAccountMember");//被檢舉人ID
		String act_id=request.getParameter("act_id");//活動ID
		String like_count=request.getParameter("like_count");//按讚數
		String post_content=request.getParameter("post_content");//貼文內容
		String post_property=request.getParameter("post_property");//貼文公開性質
		String post_time=request.getParameter("postime");//貼文時間
		String post_type=request.getParameter("post_type");//貼文類型	
		String rep_cause=request.getParameter("rep_cause");//會員寫的檢舉原因
		String post_id=request.getParameter("post_id");//貼文ID
		String reason=request.getParameter("reason");//管理員寫的檢舉原因
		
		PostBean post=new PostBean();//new PostBean物件
		MemberBean mem=new MemberBean();//new MemberBean物件
		ActivityBean act=new ActivityBean();//new ActivityBean物件
		NoticeService ns=new NoticeService();//new NoticeService物件
		ReportService rep=new ReportService();//new ReportService物件
		
		
		mem.setM_id(War_mId);
		post.setMemberBean(mem);//建立被檢舉人ID
		
		if(act_id==null||act_id.trim().length()==0){//假如被警告的貼文不屬於任何活動
			
		}else{
		Integer act_integer=Integer.valueOf(act_id.trim());//轉成Integer物件
		act.setAct_id(act_integer);
		post.setActivityBean(act);//建立活動ID
		}
		
		if(like_count==null||like_count.trim().length()==0){//假如被警告的貼文沒有任何讚
			
		}else{
		int likecount=Integer.parseInt(like_count.trim());//轉成整數
		post.setLike_count(likecount);//建立按讚數
		}
		
		post.setPost_content(post_content);//建立貼文
		//System.out.println(post_content);
		
		post.setPost_property(3);//把貼文的公開性質變為  封鎖3
	
		Timestamp ts=Timestamp.valueOf(post_time.trim());//將時間的字串轉換成Timestamp物件
		//System.out.println("Timestamp="+ts);
		post.setPost_time(ts);//設定時間
		
		int type=Integer.parseInt(post_type.trim());//轉成Integer物件
		post.setPost_type(type);//建立公開類別
		
		int postId=Integer.parseInt(post_id.trim());//將型態為字串的post_id轉換整數型態
		post.setPost_id(postId);//設定post_id
		
		PostBean pos=rep.LockPost(post);//封鎖貼文
		//System.out.println(pos.getPost_content());
		WarningNoticeBean war=ns.insertWarningNotice(pos, reason);//加入檢舉通知
		List<ReportBean> replist=rep.deleteReports(post);//刪除相同被檢舉貼文的檢舉
		
		int count=rep.updateViolationCount(War_mId);//紀錄會員被檢舉的次數
//		System.out.println("會員ID="+War_mId);
//		System.out.println("次數="+count);
		if(count==3){//假如被檢舉次數達到3次，則封鎖會員帳號
			rep.LockAccount(War_mId);
		}else{
			
		}
		
		//////////////
		ReportService p = new ReportService();
		NoticeService n = new NoticeService();

		List<ReportBean> reportlist = p.viewAllReportPost();

		request.setAttribute("Report", reportlist);

		if(war!=null && replist!=null){
//		 RequestDispatcher rd = request.getRequestDispatcher("/pages/ConnectReport/Send_Warnotice_success.jsp");
		 RequestDispatcher rd = request.getRequestDispatcher("/pages/ManagerReport.jsp");
         rd.forward(request, response);
		}else{
//			RequestDispatcher rd = request.getRequestDispatcher("/pages/ConnectReport/Send_Warnotice_error.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ManagerReport.jsp");
	        rd.forward(request, response);
		}
	}



}
