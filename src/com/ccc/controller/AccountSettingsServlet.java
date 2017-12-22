package com.ccc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.MemberService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class AccountSettingsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");

			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);

			int cf_birth = Integer.parseInt(req.getParameter("cf_birth"));
			int cf_email = Integer.parseInt(req.getParameter("cf_email"));
			int cf_intro = Integer.parseInt(req.getParameter("cf_intro"));
			int cf_sex = Integer.parseInt(req.getParameter("cf_sex"));
			int cf_post = Integer.parseInt(req.getParameter("cf_post"));
			int cf_res = Integer.parseInt(req.getParameter("cf_res"));
			int cf_act = Integer.parseInt(req.getParameter("cf_act"));
			int cf_theme = Integer.parseInt(req.getParameter("cf_theme"));
			mb.setCf_birth(cf_birth);
			mb.setCf_email(cf_email);
			mb.setCf_intro(cf_intro);
			mb.setCf_sex(cf_sex);
			mb.setCf_post(cf_post);
			mb.setCf_res(cf_res);
			mb.setCf_act(cf_act);
			mb.setCf_theme(cf_theme);

			MemberService ms = new MemberService();
			mb = ms.accountSettings(mb);
			req.setAttribute("settings", mb);

			RequestDispatcher rd = req.getRequestDispatcher("/pages/Settings.jsp");
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