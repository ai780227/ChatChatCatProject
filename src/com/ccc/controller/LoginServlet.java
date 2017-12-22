package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.CatDAO;
import com.ccc.model.service.FriendService;
import com.ccc.model.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServletContext servletContext = null;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginService loginServ = new LoginService();
		FriendService friendServ = new FriendService();
		ServletContext servletContext = request.getServletContext();
		HttpSession session = request.getSession();
//		System.out.println("request.getSession");
		
		//取得帳密
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//登入
		MemberBean mb = loginServ.loginMember(id, pwd);
		//如果不為null，表示登入成功，並將資料塞進Session、ServletContext
		if(mb!=null) {
			int m_authority = mb.getM_authority();		//取得帳號權限
			//先確認帳號是否被封鎖
			if(m_authority == 2) {
				Timestamp m_block_time = mb.getM_block_time();
				Timestamp m_unblock_time = new Timestamp((long) (m_block_time.getTime() + (30*24*60*60*1000f)) );		//解除封鎖時間 = 封鎖時間 + 30天
				Timestamp now_time = new Timestamp(System.currentTimeMillis());
				//如果  (現在時間-封鎖時間) < 1個月  → 依然封鎖中，導回index.jsp
				if( (now_time.getTime() - m_block_time.getTime()) < (float)(30*24*60*60*1000f)) {
					request.setAttribute("AccountLock", "該帳號封鎖中，至" + m_unblock_time.toString());
//					RequestDispatcher rd = request.getRequestDispatcher("/pages/RightSideBar.jsp");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
//					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//					rd.forward(request, response);
					return;
				} else {
					//超過封鎖時間，解除封鎖
					loginServ.unlockMember(mb);
				}
			}
			
			//帳號沒有被封鎖/解除封鎖，繼續登入動作
			WebApplicationContext webAppCtx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			CatDAO catdao = (CatDAO) webAppCtx.getBean("CatDAO");
			List<CatBean> catList = catdao.getCatsByM_id(mb.getM_id());
			List<MemberBean> friendsList = friendServ.MyFriendName(mb);			
			//設定session存活時間 - 30分鐘
			session.setMaxInactiveInterval(1800000);
			//將資料(member, friends, cats) 塞進session
			session.setAttribute("user", mb);
			session.setAttribute("friends", friendsList);
			session.setAttribute("cats", catList);
			//將資料(member) 塞進ServletContext，用以判斷有哪些人已登入
			servletContext.setAttribute(mb.getM_id(),mb);
			//轉送至登入成功頁面
			response.sendRedirect(request.getContextPath() + "/pages/HomePage.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("/pages/HomePage.jsp");
//			rd.forward(request, response);
			return;
		} else {
//			RequestDispatcher rd = request.getRequestDispatcher("/pages/RightSideBar.jsp");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//			rd.forward(request, response);
			return;
		}
	}

}
