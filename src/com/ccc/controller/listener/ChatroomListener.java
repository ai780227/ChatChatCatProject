package com.ccc.controller.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.MessageBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.ChatroomService;
import com.ccc.model.service.event.ChatroomEvent;

public class ChatroomListener implements ServletContextAware,
		ApplicationListener {

	static ServletContext sctx = null;

	@Override
	public void setServletContext(ServletContext sctx) {
		// TODO Auto-generated method stub
//		System.out.println("It's setServletContext");
		this.sctx = sctx;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
//		System.out.println("It's onApplicationEvent");
		if (event instanceof ChatroomEvent) {
			WebContext webContext = WebContextFactory.get(); // 取得webContext
			MessageBean msgbean = (MessageBean) event.getSource(); // 取得Client端傳送的MessageBean
			ServletContext sltContext = webContext.getServletContext(); // 取得Server的
																		// ServletContext
			// 取得存在ServletContext中的所有 ScriptSessions ("allScptSess")
			Map<String, ScriptSession> scptSessMap = (Map<String, ScriptSession>) sltContext
					.getAttribute("allScptSess");
			String scptSid = null; // 宣告Script Session id 變數

			HttpSession httpSess = webContext.getSession(); // Http Session

			MemberBean membean_from = (MemberBean) httpSess
					.getAttribute("user"); // 取得自己的member bean

			if (msgbean != null) {
				ApplicationContext appCtx = new ClassPathXmlApplicationContext("beans.config.xml");
				MemberDAO memdao = (MemberDAO) appCtx.getBean("MemberDAO");
				final String mem_from = membean_from.getM_id(); // 取得送件者ID
				final String mem_from_name = memdao.getByPrimaryKey(mem_from).getM_name();	// 取得送件者Name
				final String mem_to = msgbean.getMember_to().getM_id(); // 取得收件者ID
				String mem_to_name = memdao.getByPrimaryKey(mem_to).getM_name();	// 取得收件者Name
				final String content = msgbean.getMsg_content(); // 取得信件內容
				final String time = msgbean.getMsg_time().toString(); // 取得信件時間
				final ScriptSession scptSess = scptSessMap.get(mem_to); // 取得收件者目前之ScriptSession

				// 將聊天內容存到資料庫
				ChatroomService chatserv = new ChatroomService();
				MessageBean actMsgBean = chatserv.insertMessage(mem_from,
						mem_to, content);
				// MessageBean actMsgBean = msgbean;

				// 如果actMsgBean不為null，表示成功insert到資料庫
				if (actMsgBean != null) {

					// scptSess != null 表示收件者目前在線上，將信息push給收件者;
					if (scptSess != null) {
						scptSid = scptSess.getId(); // 取得收件者目前之ScriptSession 的id
//						System.out.println("Browser.withSession before");
						Browser.withSession(scptSid, new Runnable() {
							private ScriptBuffer script = new ScriptBuffer();

							@Override
							public void run() {
//								System.out.println("in run()");
								// TODO Auto-generated method stub
								script.appendCall("showMessage", mem_from, mem_from_name, content, time.toString());
								scptSess.addScript(script);
							}
						});
					}

				}
			}
		}
	}

}
