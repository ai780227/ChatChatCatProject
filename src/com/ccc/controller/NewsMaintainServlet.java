package com.ccc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.ManagerBean;
import com.ccc.model.bean.NewsBean;
import com.ccc.model.service.NewsService;

/**
 * Servlet implementation class NewsMaintain
 */
//@WebServlet("/NewsMaintainServlet.mgr")
public class NewsMaintainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsMaintainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 管理員看所有的新聞
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		NewsService newsServ = new NewsService();
		List<NewsBean> newsList = newsServ.getNewsOnPages(50);
		request.setAttribute("newsList", newsList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/ManagerNews.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession httpSess = request.getSession();
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		ManagerBean mgr = null;
		
		//如果尚未登入，導回index
		if(httpSess.getAttribute("manager") == null) {
			response.sendRedirect(request.getContextPath() + "/manager-index.jsp");
		} else {
			mgr = (ManagerBean) httpSess.getAttribute("manager");
		}
		
		//取得資料
		String content = request.getParameter("editor");
		String title = request.getParameter("title");
		String source = request.getParameter("source");
		String link = request.getParameter("link");
		String id = request.getParameter("id");
		
		if(title==null || title.trim().length()==0) {
			errorMsg.put("errorTitleEmpty", "必須輸入標題");
		}
		if(content==null || content.trim().length()==0) {
			errorMsg.put("errorContentEmpty", "必須輸入內容");
		}
		if(source==null || source.trim().length()==0) {
			errorMsg.put("errorSourceEmpty", "必須輸入來源");
		}
		
		NewsBean newsBean = new NewsBean();
		NewsService newsServ = new NewsService();
		newsBean.setMgr_id(mgr.getMgr_id());
		newsBean.setNews_title(title);
		newsBean.setNews_content(content);
		newsBean.setNews_time(new java.sql.Timestamp(System.currentTimeMillis()));
		newsBean.setNews_source(source);
		if(link!=null && link.trim().length()!=0)
			newsBean.setNews_link(link);
		
		//表示請求中的form有<input type="hidden" name="id">
		if(id!=null && id.trim().length()!=0) {
			try {		
				if(!errorMsg.isEmpty()) {
					RequestDispatcher rd = request.getRequestDispatcher("/pages/ManagerNews.jsp");
					rd.forward(request, response);
					return;
				}
				newsBean.setNews_id(Integer.valueOf(id));
				newsServ.editNews(newsBean);	//update news
				
				List<NewsBean> newsList = newsServ.getNewsOnPages(50);	//get news
				request.setAttribute("newsList", newsList);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/ManagerNews.jsp");
				rd.forward(request, response);
				return;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {		
			if(!errorMsg.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/pages/CreateNews.jsp");
				rd.forward(request, response);
				return;
			}
			
			newsServ.createNews(newsBean);			
			List<NewsBean> newsList = newsServ.getNewsOnPages(50);	//get news
			request.setAttribute("newsList", newsList);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ManagerNews.jsp");
			rd.forward(request, response);
			return;
		}
		
	}

}
