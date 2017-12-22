package com.ccc.model.demo;

import java.sql.Date;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sun.security.action.GetBooleanAction;

import com.ccc.model.bean.BoardBean;
import com.ccc.model.bean.NewsBean;
import com.ccc.model.dao.BoardDAO;
import com.ccc.model.dao.FriendshipDAO;
import com.ccc.model.dao.FriendshipNoticeDAO;
import com.ccc.model.dao.MessageDAO;
import com.ccc.model.dao.NewsDAO;
import com.ccc.model.dao.WhoLikeDAO;


public class MainTest_by_eddy {
	public static void main(String args[]) {
		ApplicationContext context =new ClassPathXmlApplicationContext("beans.config.xml");

//======================NEWS測試=======================================================================================
		NewsDAO newsBD=(NewsDAO)context.getBean("NewsDAO");
//		NewsDAO DD = new NewsBeanDAO();
		NewsBean newsB = new NewsBean();
//		//=============================**INSERT測試==========================
//		newsB.setNews_time(new java.sql.Timestamp(System.currentTimeMillis()));
//		newsB.setMgr_id("manager003");
//		newsB.setNews_title("測試setNews_title測試001"); 
//		newsB.setNews_content("測試setNews_content測試001");
//		newsB.setNews_source("測試setNews_source測試001");
//		newsBD.insert(newsB);
		//=============================**INSERT測試==========================
		//=============================**UPDATE測試==========================	
//		newsB.setNews_id(4);
//		newsB.setNews_time(new java.sql.Timestamp(System.currentTimeMillis()));
//		newsB.setMgr_id("manager003");
//		newsB.setNews_title("修改後修改後"); 
//		newsB.setNews_content("測試setNews_content測試001");
//		newsB.setNews_source("測試setNews_source測試001");	
//		newsBD.update(newsB);
		//=============================**UPDATE測試==========================
		//=============================**DELETE測試==========================
//		newsB.setNews_id(3);
//		newsBD.delete(newsB);
		//=============================**DELETE測試==========================
		
//		newsB = newsBD.getByPrimaryKey(1);
//		newsBD.queryByNewsBean(newsB);
		
//		 List<NewsBean> nwsa = newsBD.getAll();
//		 for (int i = 0; i < nwsa.size(); i++) {
//		 System.out.println(nwsa.get(i).getNews_id());
//		 }
		
//		 List<NewsBean> nwsa = newsBD.getByMgrId("manager002");
//		 for (int i = 0; i < nwsa.size(); i++) {
//		 System.out.println(nwsa.get(i).getNews_id());
//		 }
		
//		 List<NewsBean> nwsa = newsBD.getByTitle("貓");
//		 for (int i = 0; i < nwsa.size(); i++) {
//		 System.out.println(nwsa.get(i).getNews_id());
//		 }
		
//		 List<NewsBean> nwsa = newsBD.getByDayToDay("2014-03-14 16:30:47.303", "2014-03-20 16:30:47.303");
//		 for (int i = 0; i < nwsa.size(); i++) {
//		 System.out.println(nwsa.get(i).getNews_id());
//		 }
		
//		 List<NewsBean> nwsa = newsBD.getByNewsContent("國際");
//		 for (int i = 0; i < nwsa.size(); i++) {
//		 System.out.println(nwsa.get(i).getNews_id());
//		 }
		
//		 List<NewsBean> nwsa = newsBD.getByNewsSource("ETtoday");
//		 for (int i = 0; i < nwsa.size(); i++) {
//		 System.out.println(nwsa.get(i).getNews_id());
//		 }
	
//======================NEWS測試=======================================================================================				
				
		
//======================BOARD測試=======================================================================================
				BoardDAO boardBD=(BoardDAO)context.getBean("BoardDAO");
//				BoardBeanDAO DD = new BoardBeanDAO();
				BoardBean boardB = new BoardBean();
				//=============================**INSERT測試==========================
//				Date d = new Date(1, 1, 1);
//				boardB.setBoard_time(new java.sql.Timestamp(System.currentTimeMillis()));
//				boardB.setMgr_id("manager003");
//				boardB.setBoard_content("測試setBoard_content測試001");
//				boardBD.insert(boardB);
				//=============================**INSERT測試==========================
				//=============================**UPDATE測試==========================	
//				boardB.setBoard_id(4);
//				Date d = new Date(2, 2, 2);
//				boardB.setBoard_time(new java.sql.Timestamp(System.currentTimeMillis()));
//				boardB.setMgr_id("manager003");
//				boardB.setBoard_content("測試setBoard_content測試001");
//				boardBD.update(boardB);
				//=============================**UPDATE測試==========================
				//=============================**DELETE測試==========================
//				boardB.setBoard_id(4);
//				boardBD.delete(boardB);
				//=============================**DELETE測試==========================
				
//				boardB = boardBD.getByPrimaryKey(1);	
//				boardBD.queryByBoardBean(boardB);			
					
//				 List<BoardBean> bb = boardBD.getAll();
//				 for (int i = 0; i < bb.size(); i++) {
//				 System.out.println(bb.get(i).getBoard_id());
//				 }
				
//				 List<BoardBean> bb = boardBD.getByMgrId("manager003");
//				 for (int i = 0; i < bb.size(); i++) {
//				 System.out.println(bb.get(i).getBoard_id());
//				 }
				
//				 List<BoardBean> bb = boardBD.getByDayToDay("2014-03-17 16:30:47.303", "2014-03-20 16:30:47.303");
//				 for (int i = 0; i < bb.size(); i++) {
//				 System.out.println(bb.get(i).getBoard_id());
//				 }

//				 List<BoardBean> bb = boardBD.getByBoardContent("ChatChatCat");
//				 for (int i = 0; i < bb.size(); i++) {
//				 System.out.println(bb.get(i).getBoard_id());
//				 }
				
//======================BOARD測試=======================================================================================	
	}

}
