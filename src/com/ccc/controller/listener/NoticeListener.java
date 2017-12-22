package com.ccc.controller.listener;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.ServletContextAware;

import com.ccc.model.bean.FriendshipNoticeBean;
import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.service.event.ActivityNoticeEvent;
import com.ccc.model.service.event.FriendshipNoticeEvent;
import com.ccc.model.service.event.ResponseNoticeEvent;
import com.ccc.model.service.event.WarningNoticeEvent;

public class NoticeListener implements ApplicationListener,	ServletContextAware {

	static ServletContext sctx = null;
	Map<String,ScriptSession> scptSessMap =null;
	
	@Override
	public void setServletContext(ServletContext sctx) {
		// TODO Auto-generated method stub
//		System.out.println("It's NoticeListener setServletContext");
//		if(sctx==null)
//			System.out.println("sctx is null   (in NoticeListener setServletContext)");
//		else
//			System.out.println("sctx is not null   (in NoticeListener setServletContext)");
			
		this.sctx = sctx;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
//		System.out.println("NoticeListener onApplicationEvent");
		if(event instanceof FriendshipNoticeEvent) {
			sendFsNoticeToClient(event);
			
		} else if(event instanceof ResponseNoticeEvent) {
			sendRespNoticeToClient(event);
			
		} else if(event instanceof ActivityNoticeEvent) {
			sendActNoticeToClient(event);
			
		} else if(event instanceof WarningNoticeEvent) {
			sendWarningNoticeToClient(event);
		}
	}
	
	//推送加友通知給接收者Client端
	@SuppressWarnings("unchecked")
	public void sendFsNoticeToClient(ApplicationEvent event) {	
		FriendshipNoticeBean fsnbean = (FriendshipNoticeBean) event.getSource();		//取得通知物件
		String sendTo = fsnbean.getMember_to().getM_id();								//透過通之物鍵取得接收者之 Member id
		scptSessMap = (Map<String,ScriptSession>) sctx.getAttribute("allScptSess");		//取得存在ServletContext中的 ScripSession Map
		
		//如果scptSessMap 中有 sendTo 的 ID的話，表示對方在線上，再推送通知
		if(scptSessMap.containsKey(sendTo)) {
			final ScriptSession scptSess = scptSessMap.get(sendTo);		//用Member id 取得接收者之 ScriptSession
			if(scptSess!=null) {
				String scptSid = scptSess.getId();							//取得接收者之ScriptSession id
				
				Browser.withSession(scptSid, new Runnable() {
					private ScriptBuffer script = new ScriptBuffer();

					@Override
					public void run() {
						//加入欲呼叫之Client端function
						script.appendCall("receiveFriendNotice", "send");
						scptSess.addScript(script);
					}
					
				});						
			}	
		}
	}
	
	//推送回覆通知給接收者Client端
	public void sendRespNoticeToClient(ApplicationEvent event) {
		Set<String> midSet = (Set<String>) event.getSource();				//取得所有接收者的Member ID
		if(midSet!=null) {
			scptSessMap = (Map<String,ScriptSession>) sctx.getAttribute("allScptSess");		//取得存在ServletContext中的 ScripSession Map
			for(String m_id : midSet) {
//				System.out.println("m_id=" + m_id);
				if(scptSessMap.containsKey(m_id)) {						//如果接收者在 ScripSession Map 中，就推送通知
					final ScriptSession scptSess = scptSessMap.get(m_id);
					if(scptSess!=null) {	//如果ScriptSession不為null，表對象已經進入頁面
						Browser.withSession(scptSess.getId(), new Runnable() {
							private ScriptBuffer script = new ScriptBuffer();

							@Override
							public void run() {
								script.appendCall("receiveRespNotice", "send");
								scptSess.addScript(script);
							}
							
						});
						
					}
				}
			}
		}
	}
	
	//推送活動通知給接收者Client端
	public void sendActNoticeToClient(ApplicationEvent event) {
		Set<String> midSet = (Set<String>) event.getSource();				//取得所有接收者的Member ID
		if(midSet!=null) {
			scptSessMap = (Map<String,ScriptSession>) sctx.getAttribute("allScptSess");		//取得存在ServletContext中的 ScripSession Map
			for(String m_id : midSet) {
				if(scptSessMap.containsKey(m_id)) {						//如果接收者在 ScripSession Map 中，就推送通知
					final ScriptSession scptSess = scptSessMap.get(m_id);
					if(scptSess!=null) {	//如果ScriptSession不為null，表對象已經進入頁面
						Browser.withSession(scptSess.getId(), new Runnable() {
							private ScriptBuffer script = new ScriptBuffer();

							@Override
							public void run() {
								// TODO Auto-generated method stub
								script.appendCall("receiveActNotice", "send");
								scptSess.addScript(script);
							}
							
						});
					}
				}
			}
		}
	}
	
	//推送被警告通知給接收者Client端
	public void sendWarningNoticeToClient(ApplicationEvent event) {
		WarningNoticeBean warNotice = (WarningNoticeBean) event.getSource();
		if(warNotice!=null) {
			scptSessMap = (Map<String, ScriptSession>) sctx.getAttribute("allScptSess");
			if(scptSessMap.containsKey(warNotice.getMember().getM_id())) {
				final ScriptSession scptSess = scptSessMap.get(warNotice.getMember().getM_id());
				if(scptSess!=null) {
					Browser.withSession(scptSess.getId(), new Runnable() {
						private ScriptBuffer script = new ScriptBuffer();
						
						@Override
						public void run() {
							script.appendCall("receiveWarNotice", "successed");
							scptSess.addScript(script);
						}
						
					});
				}
			}
		}
	}

}
