package com.ccc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.ManagerBean;
import com.ccc.model.service.ManagerLoginService;

/**
 * Servlet implementation class LoginManagerServlet
 */
//@WebServlet("/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ManagerLoginService mgrLoginServ = new ManagerLoginService();
		ManagerBean mgr;
		
		//取得資料
		String mgr_id = request.getParameter("id");
		String mgr_pwd = request.getParameter("pwd");
		
		//資料驗證
		if(mgr_id!=null && mgr_pwd!=null) {
			if(mgr_id.trim().length()!=0 && mgr_pwd.trim().length()!=0) {
				mgr = mgrLoginServ.loginManager(mgr_id, mgr_pwd);
				//如果mgr不為null，表示帳號密碼正確，登入成功
				if(mgr!=null) {
					//將資料塞入至Session
					HttpSession httpSess = request.getSession();
					httpSess.setAttribute("manager", mgr);		
					RequestDispatcher rd = request.getRequestDispatcher("/pages/ManagerHomePage.jsp");
					rd.forward(request, response);
					return;			
				}
			}
		}
		//登入失敗的話，導回登入頁面
		RequestDispatcher rd = request.getRequestDispatcher("/managerIndex.jsp");
		rd.forward(request, response);
		return;
	}

}
