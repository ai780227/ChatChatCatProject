package com.ccc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.bean.ResponseBean;
import com.ccc.model.service.PostService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class DeleteResponseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");

			int res_id = Integer.parseInt(req.getParameter("res_id"));
			ResponseBean resb = new ResponseBean();
			resb.setRes_id(res_id);

			PostService ps = new PostService();
			boolean deleteResponse = ps.deleteResponse(resb);
			req.setAttribute("deleteResponse", deleteResponse);

//			RequestDispatcher rd = req.getRequestDispatcher("/pages/response.jsp");
//			rd.forward(req, resp);
//			return;
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