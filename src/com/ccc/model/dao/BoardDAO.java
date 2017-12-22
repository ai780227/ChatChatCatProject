package com.ccc.model.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.BoardBean;
import com.ccc.model.bean.NewsBean;
import com.ccc.model.dao.interfacedao.IBoardDAO;

@Transactional
public class BoardDAO implements IBoardDAO {
	Transaction tx = null;

	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
//===========================================insert=============================================================================================================
	public BoardBean insert(BoardBean boardB) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(boardB);
			return boardB;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
//==========================================update==============================================================================================================	
	public BoardBean update(BoardBean boardB) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE BoardBean SET mgr_id=:mgr_id , board_time=:board_time , board_content=:board_content WHERE board_id=:board_id";
		Query query = session.createQuery(hql);
		query.setInteger("board_id", boardB.getBoard_id());
		query.setString("mgr_id", boardB.getMgr_id());
		query.setDate("board_time", boardB.getBoard_time());
		query.setString("board_content", boardB.getBoard_content());	
		try {
			query.executeUpdate();
			return boardB;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}


	}
	
//==========================================delete==============================================================================================================
	public BoardBean delete(BoardBean boardB) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE BoardBean WHERE board_id=:board_id";
		Query query = session.createQuery(hql);
		query.setInteger("board_id", boardB.getBoard_id());
		try {
			query.executeUpdate();
			return boardB;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
//=======================================getByPrimaryKey=================================================================================================================
	@Override
	public BoardBean getByPrimaryKey(int board_id) {
		Session session = sessionFactory.getCurrentSession();
		BoardBean bb;
		
		bb = (BoardBean) session.get(BoardBean.class, board_id);
//		if (bb == null) {
//			System.out.println("There is noting.");
//		}
		try {
			return bb;				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//=======================================getAll===================================================================================================================	
	@Override
	public List<BoardBean> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<BoardBean> listAll;
		Iterator listA = null;
		try {
		String sql = "from BoardBean order by board_id desc";
		Query query = session.createQuery(sql);
		listAll = (List<BoardBean>) query.list();
			if(listAll.size()!=0){
				return listAll;
			}else{
				return null;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("錯誤");
		}
		return null;
	}

	@Override
	public List<BoardBean> getByMgrId(String value) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from BoardBean where mgr_id=:mgr_id");
			query.setString("mgr_id", value);
			List<BoardBean> bb = query.list();
			return bb;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BoardBean> getByDayToDay(String value1, String value2) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from BoardBean where board_time BETWEEN '" + value1 + "' AND '" + value2 + "'");
			List<BoardBean> bb = query.list();
			return bb;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BoardBean> getByBoardContent(String value) {
		Session session = sessionFactory.getCurrentSession();
		List<BoardBean> bb;
		try {
			bb = (List<BoardBean>) session.createQuery("from BoardBean where board_content like :board_content")
					.setString("board_content", "%" + value + "%")
					.list();
			return bb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}		
		
	@Override
	public void queryByBoardBean(BoardBean bb) {
		if (bb != null) {
			System.out.println("board_id = " + bb.getBoard_id());
			System.out.println("mgr_id = " + bb.getMgr_id());
			System.out.println("board_time = " + bb.getBoard_time());
			System.out.println("board_content = " + bb.getBoard_content());
		} else {
			System.out.println("There is nothing in the BoardBean!");
		}
	}
}		
		
		
	

