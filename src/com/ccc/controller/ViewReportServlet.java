package com.ccc.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ReportBean;
import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.dao.WarningNoticeDAO;
import com.ccc.model.service.NoticeService;
import com.ccc.model.service.ReportService;

public class ViewReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ReportService p = new ReportService();
		NoticeService n = new NoticeService();

		List<ReportBean> reportlist = p.viewAllReportPost();

		request.setAttribute("Report", reportlist);

		RequestDispatcher rd = request
				.getRequestDispatcher("/pages/ManagerReport.jsp");
		rd.forward(request, response);
	}

}
