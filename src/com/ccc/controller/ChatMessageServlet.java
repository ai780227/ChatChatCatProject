package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.MessageBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.ChatroomService;

/**
 * Servlet implementation class ChatMessageServlet
 */
//@WebServlet("/ChatMessageServlet.do")
public class ChatMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		HttpSession httpSess = request.getSession();
		MemberBean user = (MemberBean) httpSess.getAttribute("user");
		ChatroomService ctServ = new ChatroomService();
		List<MessageBean> msgList = null;
		
		//取得資料
		String friend = request.getParameter("friend");
		int page = Integer.valueOf(request.getParameter("page"));
		msgList = ctServ.getEachOtherMessage(user.getM_id(), friend, page);
/*		JSONArray msgJson = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));
//		JSONArray.fromObject(msgList, jsonConfig);
		System.out.println("msgJson:" + JSONArray.fromObject(msgList, jsonConfig).toString());
//		for(MessageBean msg : msgList) {
			msgJson.element("from", msg.getMember_from().getM_id());
			msgJson.element("to", msg.getMember_to().getM_id());
			msgJson.element("content", msg.getMsg_content());
			msgJson.element("time", msg.getMsg_time().toString());
//		}*/
		
		StringBuffer strbuff = new StringBuffer();
		strbuff.append("[");
		JSONObject msgJson = new JSONObject();
		int i = 0;
		WebApplicationContext webAppCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		MemberDAO memdao = (MemberDAO) webAppCtx.getBean("MemberDAO");
		for(MessageBean msg : msgList) {
			MemberBean fromBean = memdao.getByPrimaryKey(msg.getMember_from().getM_id());
			MemberBean toBean = memdao.getByPrimaryKey(msg.getMember_to().getM_id());
			strbuff.append("{");
			strbuff.append("\"from\":\""+msg.getMember_from().getM_id()+"\"");
			strbuff.append(",");
			strbuff.append("\"from_name\":\""+fromBean.getM_name()+"\"");
			strbuff.append(",");
			strbuff.append("\"to\":\""+msg.getMember_to().getM_id()+"\"");
			strbuff.append(",");
			strbuff.append("\"to_name\":\""+toBean.getM_name()+"\"");
			strbuff.append(",");
			strbuff.append("\"content\":\""+msg.getMsg_content()+"\"");
			strbuff.append(",");
			strbuff.append("\"time\":\""+msg.getMsg_time().toString()+"\"");
			strbuff.append("}");
			if(++i < msgList.size())
				strbuff.append(",");
		}

		strbuff.append("]");
//		System.out.println(strbuff.toString());
		pw.write(strbuff.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
