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

import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.MessageBean;
import com.ccc.model.dao.interfacedao.IMessageDAO;

/*
 * 聊天紀錄   DAO
 * table: 聊天紀錄 Messages
 */
@Transactional
public class MessageDAO implements IMessageDAO {

	Transaction tx = null;
	private int yn = 0;

	// Spring sessionFactory
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ===========================================insert=============================================================================================================
	@Override
	public MessageBean insert(MessageBean mB) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(mB);
			return mB;
		} catch (Exception ex) {
			return null;
		}
		
	}

	// ===========================================update=============================================================================================================
	@Override
	public MessageBean update(MessageBean mB) { // 會員A 更改
		/*
		 * session = DefaultFactory.getSessionFactory().openSession(); tx =
		 * session.beginTransaction(); String hql =
		 * "UPDATE FriendshipBean SET friend_check=:friend_check  WHERE m_id_a=:m_id_a and m_id_b=:m_id_b"
		 * ; Query query = session.createQuery(hql); query.setString("m_id_a",
		 * mB.getM_id_a()); query.setString("m_id_b", mB.getM_id_b());
		 * query.setInteger("friend_check", mB.getFriend_check()); try { yn =
		 * query.executeUpdate(); tx.commit(); } catch (HibernateException ex) {
		 * tx.rollback(); } session.close(); if (yn != 1) { return null; } else
		 * { return mB; }
		 */
		return null;
	}

	// ===========================================delete=============================================================================================================
	@Override
	public MessageBean delete(MessageBean mB) { // 會員A 把 "與" 會員B聊天的資料刪除??
		Session session = sessionFactory.getCurrentSession();
		int yn = 0;
		String hql = "DELETE MessageBean WHERE m_id_from=:m_id_from and m_id_to=:m_id_to";

		try {
			Query query = session.createQuery(hql);
			query.setString("m_id_from", mB.getMember_from().getM_id());
			query.setString("m_id_to", mB.getMember_to().getM_id());
			yn = query.executeUpdate();
			tx.commit();
			if (yn != 1) {
				return null;
			} else {
				return mB;
			}
		} catch (Exception ex) {
			return null;
		}
		
	}

	// ===========================================getByPrimaryKey=============================================================================================================
	@Override
	public MessageBean getByPrimaryKey(int msg_id) {
		Criteria criteria;
		Session session = sessionFactory.getCurrentSession();
		try {
			criteria = session.createCriteria(MessageBean.class);
			criteria.add(Restrictions.eq("msg_id", msg_id));
			List act = criteria.list();
			if (act.isEmpty()) { // 判斷是否有資料
				return null;
			} else {
				Iterator user = act.iterator();
				while (user.hasNext()) {
					MessageBean users = (MessageBean) user.next();
					return users;
				}
			}
		} catch (Exception ex) {
			return null;
		}
		return null;
	}

	// ===========================================getAll=============================================================================================================
	@Override
	public List<MessageBean> getAll() {
		List<MessageBean> listAll;
		Iterator listA;
		Session session = sessionFactory.getCurrentSession();
		String sql = "from MessageBean Messages";
		try {
			Query query = session.createQuery(sql);
			listAll = (List<MessageBean>) query.list();
			Iterator user = listAll.iterator();
			if (listAll.size() == 0) {
				return null;
			} else {
				return listAll;
			}
		} catch (Exception ex) {
			return null;
		}

	}
	
	// ===========================================getMessageByMethod=============================================================================================================
	@Override
	public List<MessageBean> getMessageByMethod(String query) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query q = session.createQuery(query);
			List<MessageBean> message = q.list();
			return message;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	//取得前n筆資料
	// ===========================================getMessageByMethodOnPage=============================================================================================================
	public List<MessageBean> getMessageByMethodOnPage(String query,int page) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query q = session.createQuery(query).setMaxResults(page);
			List<MessageBean> message = q.list();
			return message;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}