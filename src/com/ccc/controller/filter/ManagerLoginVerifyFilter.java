package com.ccc.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ManagerLoginVerifyFilter
 */
//@WebFilter("/ManagerLoginVerifyFilter")
@WebFilter({"/mgr-pages/*","*.mgr"})
public class ManagerLoginVerifyFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ManagerLoginVerifyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		HttpSession httpSess = null;
		
//		System.out.println("Manager Filter");
		//轉換HTTP request response
		if(request instanceof HttpServletRequest &&
				response instanceof HttpServletResponse) {
			req = (HttpServletRequest) request;
			resp = (HttpServletResponse) response;
		}
		
		httpSess = req.getSession();
		
		//httpSession 如果沒有 manager Attribute，表示管理員尚未登入，導回manager-index.jsp 
		if(httpSess.getAttribute("manager") == null) {
			resp.sendRedirect(req.getContextPath() + "/pages/goto-manager-index.jsp");
			return;
		}
		
//		System.out.println("manager doFilter");
		// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
