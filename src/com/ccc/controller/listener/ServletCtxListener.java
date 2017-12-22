package com.ccc.controller.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.directwebremoting.ScriptSession;

/**
 * Application Lifecycle Listener implementation class ChatWebListener
 *
 */
@WebListener
public class ServletCtxListener implements ServletContextListener {
	
	//Map< member_id, script_session_id>
	public static Map<String,ScriptSession> scptSessMap = new HashMap<String,ScriptSession>();
    /**
     * Default constructor. 
     */
    public ServletCtxListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        // TODO Auto-generated method stub
//    	System.out.println("sevletcontext created");
    	ServletContext sltContext = event.getServletContext();
    	sltContext.setAttribute("allScptSess", scptSessMap);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
