package com.ccc.controller.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.directwebremoting.ScriptSession;

import com.ccc.model.bean.MemberBean;

/**
 * Application Lifecycle Listener implementation class WebHttpSessionListener
 *
 */
@WebListener
public class WebHttpSessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public WebHttpSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
//    	System.out.println("session created");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event) {
    	MemberBean mem = (MemberBean) event.getSession().getAttribute("user");
    	ServletContext servletCtx = event.getSession().getServletContext();
    	if(mem!=null) {
    		Map<String,ScriptSession> scptSessMap = 
    				(Map<String,ScriptSession>) servletCtx.getAttribute("allScptSess");
    		//將該使用者的ScriptSession從scptSessMap中移除
    		if(scptSessMap.containsKey(mem.getM_id())) {
    			scptSessMap.remove(mem.getM_id());
//    			System.out.println("sessionDestroyed");
    		}
    	}
    }
	
}
