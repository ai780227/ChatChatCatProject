package com.ccc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.bean.NewsBean;
import com.ccc.model.service.NewsService;

/**
 * Servlet implementation class ShowNewsServlet
 */
//@WebServlet("/ShowNewsServlet")
public class ShowNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		NewsService newsServ = new NewsService();
		List<NewsBean> newsList = newsServ.getNewsOnPages(10);
		request.setAttribute("newsList", newsList);
		
//		RequestDispatcher rd = request.getRequestDispatcher("/mgr-pages/show_news.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("/pages/News_content.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
