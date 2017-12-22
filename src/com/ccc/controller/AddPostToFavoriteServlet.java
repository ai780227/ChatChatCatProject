package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ccc.model.bean.FavoriteBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.service.PostService;

public class AddPostToFavoriteServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
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
			String path = req.getParameter("path");
			PostBean pb = new PostBean();
			pb.setPost_id(post_id);
			FavoriteBean fb = new FavoriteBean();
			fb.setMemberBean(mb);
			fb.setPostBean(pb);

			PostService ps = new PostService();
			boolean addPostToFavorite = ps.addPostToFavorite(fb);
//			req.setAttribute("addPostToFavorite", addPostToFavorite);

//			RequestDispatcher rd = req.getRequestDispatcher("/pages/" + path);
//			rd.forward(req, resp);
//			return;

			PrintWriter pw = resp.getWriter();
			JSONObject json = new JSONObject();
			json.put("addPostToFavorite", addPostToFavorite);
//			json.put("favUser", mb.getM_name());
			pw.print(json.toString());
			pw.close();

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