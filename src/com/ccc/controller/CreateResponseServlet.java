package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.service.NoticeService;
import com.ccc.model.service.PostService;

public class CreateResponseServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			req.setCharacterEncoding("UTF-8");

			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);
			int post_id = Integer.parseInt(req.getParameter("post_id"));
			PostBean pb = new PostBean();
			pb.setPost_id(post_id);

			String res_content = req.getParameter("res_content");
			ResponseBean resb = new ResponseBean();
			resb.setPostBean(pb);
			resb.setMemberBean(mb);
			resb.setRes_content(res_content);

			PostService ps = new PostService();
			resb = ps.createResponse(resb);
			resb = ps.viewAResponse(resb.getRes_id());
			req.setAttribute("res", resb);

			NoticeService ns = new NoticeService();
			ns.insertResponseNotice(resb);

//			RequestDispatcher rd = req.getRequestDispatcher("/pages/response.jsp");
//			rd.forward(req, resp);
//			return;

			PrintWriter pw = resp.getWriter();
			JSONObject json = new JSONObject();
			json.put("resUserId", mb.getM_id());
			json.put("resUser", mb.getM_name());
			json.put("res_id", resb.getRes_id());
			json.put("res_time", resb.getRes_time().toString());
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