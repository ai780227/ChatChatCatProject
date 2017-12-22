package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.service.LoginService;

/**
 * Servlet implementation class AccountCheckServlet
 */
//@WebServlet("/AccountCheckServlet")
public class AccountCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		LoginService loginServ = new LoginService();
//		System.out.println("check account");
		//取得資料
		String check = request.getParameter("check");
		if("username".equals(check)) {
			String m_id = request.getParameter("username");
//			System.out.println("check username=" + m_id);
			if(m_id!=null && m_id.trim().length()!=0) {
				if(loginServ.idExists(m_id)) {
//					pw.write("此帳號已存在");
					pw.write("false");
					return;
				} else {
//					pw.write("此帳號可使用");
					pw.write("true");
					return;
				}
			}
		} else if("email".equals(check)) {
			String m_email = request.getParameter("email");
//			System.out.println(m_email);
			if(m_email!=null && m_email.trim().length()!=0) {
//				System.out.println("email");
				if(loginServ.emailExists(m_email)) {
//					pw.write("此信箱已存在");
					pw.write("false");
					return;
				} else {
//					pw.write("此信箱可使用");
					pw.write("true");
					return;
				}
			}			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
