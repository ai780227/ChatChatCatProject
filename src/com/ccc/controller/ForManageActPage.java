package com.ccc.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.ActivityService;

/**
 * Servlet implementation class ForManageActPage
 */
public class ForManageActPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//呼叫session
		HttpSession session = request.getSession();
		//呼叫ActivityService
		ActivityService actService = new ActivityService();
		//設定虛擬會員資料
		MemberBean mem = (MemberBean)session.getAttribute("user");	
		//抓取該會員的所有尚未過期的活動(包跨公開與私人)
		List<ActivityBean> acts = actService.viewActByTheMember(mem);
		//把List<ActivityBean>放入request
		request.setAttribute("myacts", acts);		
		//讀取欲參加人數
		List<Integer> act_id = new ArrayList<Integer>();
		Iterator<ActivityBean> it = acts.iterator();
		while(it.hasNext()){
			act_id.add(actService.viewCountOfTheActWhoJoin(it.next()));
		}
		//存入每筆活動的參與人數
		request.setAttribute("count", act_id);
		//存入迴圈控制max量
		Integer length = act_id.size();
		request.setAttribute("size", length);		
		//轉交頁面
		RequestDispatcher view = request.getRequestDispatcher("/pages/manageAct.jsp");
		view.forward(request, response);		
	}
}
