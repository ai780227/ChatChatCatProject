package com.ccc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.service.ActivityService;
import com.ccc.model.service.PostService;

//@WebServlet("/ShowThisActServlet")
public class ShowThisActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//建立session
		HttpSession session = request.getSession();
		//抓取該會員
		MemberBean mem = (MemberBean)session.getAttribute("user");
		//建立ActivityService物件
		ActivityService actService = new ActivityService();
		
		Integer act_id = null;
		ActivityBean act = null;
		System.out.println("request.getParameter(Act_id) = " + request.getParameter("Act_id"));
		if(Integer.valueOf(request.getParameter("Act_id"))==null){
			act = (ActivityBean)request.getAttribute("fromViewAct");
			act_id = act.getAct_id();
		}else{
			//抓取該活動
			act_id = Integer.valueOf(request.getParameter("Act_id"));
			//抓取該活動
			act = actService.getByPrimaryKeyAct(act_id);
		}
		
		
		
		
		//把該活動存入request
		request.setAttribute("thisAct", act);
		//取得該活動參加人數
		Integer length = actService.viewCountOfTheActWhoJoin(act);
		//把活動參加人數存入request
		request.setAttribute("count", length);
		//判斷該會員是否有參加該活動
		boolean i = actService.isYou(mem, act);
		//存入該判斷
		request.setAttribute("yesOrNo", i);

		//////////////////////////////////////////
		int page, size, post_type, post_property = 0;
		List<PostBean> lpb;

		PostService ps = new PostService();
		PostBean pb;
		
		MemberBean mb = (MemberBean)session.getAttribute("user");
		System.out.println(mb.getM_id());
		page = 0;
		size = 3;

		post_type = 0;
		post_property = 5;
		pb = new PostBean();
		pb.setPost_type(post_type);
		pb.setPost_property(post_property);
		
		lpb = ps.viewActPosts(mb, act_id, page, size);
		request.setAttribute("post_type", post_type);
		request.setAttribute("post_property", post_property);
		request.setAttribute("act_id", act_id);
		request.setAttribute("posts", lpb);

		Iterator<PostBean> ipb = lpb.iterator();
		List<PostPictureBean> lppb = new ArrayList<PostPictureBean>();
		while (ipb.hasNext()) {
			pb = ipb.next();
			lppb.addAll(ps.viewPostPictures(pb));
		}
		if (lppb != null) {
			request.setAttribute("postPictures", lppb);
		}

		//////////////////////////////////////////

		//轉交頁面
		RequestDispatcher view = request.getRequestDispatcher("/pages/viewAct.jsp");
		view.forward(request, response);
	}
}
