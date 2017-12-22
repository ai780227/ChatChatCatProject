package com.ccc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.CatDAO;
import com.ccc.model.service.MemberService;

/**
 * Servlet implementation class ViewOneCatInfoServlet
 */
@WebServlet("/ViewOneCatInfoServlet.do")
public class ViewOneCatInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOneCatInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
			
			String c_id = req.getParameter("c_id");

			
			
			WebApplicationContext webAppCtx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			CatDAO catdao = (CatDAO) webAppCtx.getBean("CatDAO");
			CatBean cat = catdao.getByPrimaryKey(Integer.valueOf(c_id));		

			req.setAttribute("cat", cat);

			RequestDispatcher rd = req.getRequestDispatcher("/pages/EditCat.jsp");
			rd.forward(req, resp);
			return;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
