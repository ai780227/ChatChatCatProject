package com.ccc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ReportBean;
import com.ccc.model.service.PostService;

public class AccusePostServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");

			// data for testing
			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);

			int post_id = Integer.parseInt(req.getParameter("post_id"));
			String rep_cause = req.getParameter("rep_cause");
			String other_cause = req.getParameter("other_cause");
			PostBean pb = new PostBean();
			pb.setPost_id(post_id);
			ReportBean rb = new ReportBean();
			rb.setMember(mb);
			rb.setPost(pb);
			if (rep_cause.equals("5.其他")) {
				rb.setRep_cause(other_cause);
			} else {
				rb.setRep_cause(rep_cause);
			}

			PostService ps = new PostService();
			boolean accusePost = ps.accusePost(rb);
			req.setAttribute("accusePost", accusePost);

			RequestDispatcher rd = req.getRequestDispatcher("/pages/NewReport.jsp");
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