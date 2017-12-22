package com.ccc.model.service;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.ccc.model.bean.MemberBean;

public class DwrPush {

	public void onPageLoad() { 
		Map<String,ScriptSession> scptSessMap = null;
		WebContext webContext = WebContextFactory.get();
		ServletContext sltContext = webContext.getServletContext();	
		
		ScriptSession scptSess = webContext.getScriptSession();
		HttpSession httpSess =  webContext.getSession();
        //若HttpSession有user，表示會員已登入
        //將user ID 及 ScriptSession 物件 存入 ServletContext 中的 Map (allScptSessId)
        if(httpSess.getAttribute("user") != null) {
        	scptSessMap = (Map<String,ScriptSession>) sltContext.getAttribute("allScptSess");
        	MemberBean mb = (MemberBean) httpSess.getAttribute("user");
        	scptSessMap.put(mb.getM_id(), scptSess);
        }
	} 
}
