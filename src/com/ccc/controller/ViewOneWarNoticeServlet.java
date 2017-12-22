package com.ccc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.WarningNoticeDAO;

/**
 * Servlet implementation class ViewOneWarNotice
 */

public class ViewOneWarNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOneWarNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String war_notid=request.getParameter("war_notid");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		WarningNoticeDAO wardao=(WarningNoticeDAO) context.getBean("WarningNoticeDAO");
		int warnotid=Integer.parseInt(war_notid.trim());
		WarningNoticeBean wn=wardao.getByPrimaryKey(warnotid);
		
		request.setAttribute("oneNotice", wn);
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/WarningNotice.jsp");
        rd.forward(request, response);
		

		
		
		
		
	}

}
