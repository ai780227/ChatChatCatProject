package com.ccc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.MemberService;

public class ViewCatsInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);

			MemberService ms = new MemberService();

			List<CatBean> lcb = ms.viewCatsInfo(mb);
			req.setAttribute("cats", lcb);

			RequestDispatcher rd = req.getRequestDispatcher("/pages/CatManage.jsp");
			rd.forward(req, resp);
			return;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}