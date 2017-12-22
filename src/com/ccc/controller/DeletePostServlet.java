package com.ccc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.bean.PostBean;
import com.ccc.model.service.PostService;

public class DeletePostServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");

			int post_id = Integer.parseInt(req.getParameter("post_id"));
			PostBean pb = new PostBean();
			pb.setPost_id(post_id);

			PostService ps = new PostService();
			ps.deletePost(pb);

			RequestDispatcher rd = req.getRequestDispatcher("/pages/DeleteConfirm.jsp");
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