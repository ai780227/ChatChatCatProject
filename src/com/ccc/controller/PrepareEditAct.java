package com.ccc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityParticipateBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.ActivityParticipateDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.ActivityService;
import com.ccc.model.service.FriendService;

/**
 * Servlet implementation class PrepareEditAct
 */
public class PrepareEditAct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//呼叫session
		HttpSession session = request.getSession();
		//建立ActivityService物件
		ActivityService actService = new ActivityService();
		//判斷動作
		if(request.getParameter("doWhat").equals("編輯活動")){
			//讀取act_id
			Integer act_id = Integer.valueOf(request.getParameter("Act_id"));
			//用act_id讀取該活動
			ActivityBean act = actService.getByPrimaryKeyAct(act_id);	
			//該活動放入request
			request.setAttribute("editTheAct", act);	
			//搜尋尚未加入該會員發起的活動的好友
			MemberBean theMem=(MemberBean)session.getAttribute("user");
			List<MemberBean> myFriendsNotInMyAct = actService.viewMyFriendNotInMyAct(act_id, theMem);
			//把尚未加入該會員發起的活動的好友放入request
			request.setAttribute("myfri", myFriendsNotInMyAct);
			//尚未加入該會員活動的好友人數
			Integer number = myFriendsNotInMyAct.size();
			//把尚未加入該會員活動的好友人數放入request
			request.setAttribute("myfriquantity", number);
			//頁面轉交
			RequestDispatcher view = request.getRequestDispatcher("/pages/editAct.jsp");
			view.forward(request, response);	
		}else if(request.getParameter("doWhat").equals("刪除活動")){
			//讀取act_id
			Integer act_id = Integer.valueOf(request.getParameter("Act_id"));
			//用act_id讀取該活動
			ActivityBean act = actService.getByPrimaryKeyAct(act_id);	
			//刪除該筆活動
			actService.deleteAct(act);
			//轉移頁面
			RequestDispatcher view = request.getRequestDispatcher("/ForManageActPage.do");
			view.forward(request, response);
		}else if(request.getParameter("doWhat").equals("發起活動")){
			//抓存在session的會員
			MemberBean mem = (MemberBean)session.getAttribute("user");
			//呼叫FriendService
			FriendService friService = new FriendService();
			List<MemberBean> myFriends = friService.MyFriendName(mem);	
			//好友List存入request
			request.setAttribute("friendsList", myFriends);
			//轉交頁面
			RequestDispatcher view = request.getRequestDispatcher("/pages/createAct.jsp");
			view.forward(request, response);	
		}else if(request.getParameter("doWhat").equals("新增活動")){
			//建立測試用DAO物件
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
			MemberDAO memDAO = (MemberDAO) context.getBean("MemberDAO");
			ActivityParticipateDAO parDAO = (ActivityParticipateDAO)context.getBean("ActivityParticipateDAO");
			//建立ActivityBean物件
			ActivityBean act = new ActivityBean();
			//塞入不用特殊處理的資料
			act.setMember((MemberBean)session.getAttribute("user"));
			act.setAct_title((request.getParameter("act_title")));
			act.setAct_location(request.getParameter("act_location"));
			act.setAct_content(request.getParameter("act_content"));
			
			//把活動屬性存入request	
			int pubOrpri = Integer.parseInt(request.getParameter("act_property"));
			act.setAct_property(pubOrpri);
			request.setAttribute("property", pubOrpri);	

			//處理日期轉換
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			try {
					Date act_time = sdf.parse(request.getParameter("act_time").toString());
					act.setAct_time(new Timestamp(act_time.getTime()));
				//判斷活動報名截止日是否為NULL
				if(request.getParameter("act_deadline")!=""){
					//把活動截止日存入request
					Date act_deadline = sdf.parse(request.getParameter("act_deadline").toString());
					act.setAct_deadline(new Timestamp(act_deadline.getTime()));
					request.setAttribute("deadline", new Timestamp(act_deadline.getTime()));					
					//Date act_deadline = sdf.parse(request.getParameter("act_deadline").toString());
					//act.setAct_deadline(new Timestamp(act_deadline.getTime()));	
				}			
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("日期轉換錯誤");
			}
			
			//建立活動以及把活動放入request
			ActivityBean newActBean = actService.createAct(act);
			request.setAttribute("newAct",act);
			//設立自己為活動關係人
			ActivityParticipateBean par = new ActivityParticipateBean();
			par.setMember((MemberBean)session.getAttribute("user"));
			par.setActivity(act);
			par.setAct_participate(1);
			parDAO.insert(par);
			
			//如果有點選擇好友,則新增活動關係人以及活動通知以及並且把好友名單放入request
			if(request.getParameterValues("friendsName")!=null){
				String[] formdata  = request.getParameterValues("friendsName");
				List<String> myFriendsId = Arrays.asList(formdata);
				actService.createPars(myFriendsId,newActBean);
				actService.createNots(myFriendsId,newActBean);
				
				//為了MemberName而做的處理
				List<MemberBean> mems = new ArrayList();
				Iterator<String> it = myFriendsId.iterator();
				while(it.hasNext()){
					mems.add(memDAO.getByPrimaryKey(it.next()));
				}
				request.setAttribute("friendsName",mems);
			}		
			//轉交頁面		
			RequestDispatcher view = request.getRequestDispatcher("/pages/checkNewAct.jsp");
			view.forward(request, response);
		}
	}

}
