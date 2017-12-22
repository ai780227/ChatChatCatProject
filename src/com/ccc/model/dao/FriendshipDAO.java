package com.ccc.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.dao.interfacedao.IFriendshipDAO;

/*
 * 好友關係    DAO
 * table：好友關係 Friendship
 */
@Transactional
public class FriendshipDAO implements IFriendshipDAO {

	Transaction tx = null;
	private int yn = 0;

	// Spring sessionFactory
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ===========================================insert=============================================================================================================
	@Override
	public FriendshipBean insert(FriendshipBean fsB) { // 新增資料
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(fsB);
			return fsB;
		} catch (Exception ex) {
			return null;
		}
	}

	// ==========================================update==============================================================================================================
	@Override
	public FriendshipBean update(FriendshipBean fsB) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE FriendshipBean SET friend_check=:friend_check  WHERE m_id_a=:m_id_a and m_id_b=:m_id_b";
		Query query = session.createQuery(hql);
		query.setString("m_id_a", fsB.getMemberBean_a().getM_id());
		query.setString("m_id_b", fsB.getMemberBean_b().getM_id());
		query.setInteger("friend_check", fsB.getFriend_check());
		try {
			yn = query.executeUpdate();
			if (yn != 1) {
				return null;
			} else {
				return fsB;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	// ==========================================delete==============================================================================================================
	@Override
	public FriendshipBean delete(FriendshipBean fsB) {
		Session session = sessionFactory.getCurrentSession();
		int yn = 0;
		String hql = "DELETE FriendshipBean WHERE m_id_a=:m_id_a and m_id_b=:m_id_b";
		Query query = session.createQuery(hql);
		query.setString("m_id_a", fsB.getMemberBean_a().getM_id());
		query.setString("m_id_b", fsB.getMemberBean_b().getM_id());
		try {
			yn = query.executeUpdate();
			if (yn != 1) {
				return null;
			} else {
				return fsB;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	// =======================================getByPrimaryKey=================================================================================================================
	@Override
	public FriendshipBean getByPrimaryKey(String m_id_a, String m_id_b) {
		FriendshipBean item = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from FriendshipBean where m_id_a=:m_id_a and m_id_b=:m_id_b");
		query.setString("m_id_a", m_id_a);
		query.setString("m_id_b", m_id_b);
		try {
			List<FriendshipBean> act = query.list();
			if (act.size() != 0) {
				FriendshipBean FriendshipBean = act.get(0);
				return FriendshipBean;
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	// =======================================getAll===================================================================================================================
	@Override
	public List<FriendshipBean> getAll() {
		List<FriendshipBean> listAll;
		Session session = sessionFactory.getCurrentSession();
		String sql = "from FriendshipBean Friendship";
		try {
			Query query = session.createQuery(sql);
			listAll = (List<FriendshipBean>) query.list();
			if (listAll.size() != 0) {
				return listAll;
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}

	}

	// =======================================getMyFriend===================================================================================================================
	@SuppressWarnings("unchecked")
	public List<FriendshipBean> getMyFriend(MemberBean memberBean_from) {
		List<FriendshipBean> listMe;
		List<FriendshipBean> listYou;
		List<FriendshipBean> listReMyFriends = new ArrayList<FriendshipBean>();
		Session session = sessionFactory.getCurrentSession();
		//HQL我加的人
		String sql  = "from FriendshipBean where m_id_a=:memberBean_from and friend_check=1";
		//HQL加我好友的人
		String sql2 = "from FriendshipBean where m_id_b=:memberBean_from and friend_check=1";
		
		
		try {
			Query query = session.createQuery(sql);
			Query query2 = session.createQuery(sql2);
			 query.setString("memberBean_from", memberBean_from.getM_id());
			query2.setString("memberBean_from", memberBean_from.getM_id());
			listMe = (List<FriendshipBean>) query.list(); // 取自己加的好友
		
			listYou = (List<FriendshipBean>) query2.list(); // 取對方加我好友
			
			
		
			
			for (int i = 0; i < listMe.size(); i++) {
				for (int j = 0; j <listYou.size(); j++) {
					if (listMe.get(i).getMemberBean_b().getM_id().equals(listYou
							.get(j).getMemberBean_a().getM_id())) {
						listReMyFriends.add(listMe.get(i));
						
					}
				}
				
			}
		
			return listReMyFriends;
		} catch (Exception ex) {
			return null;
		}
	}
	//=====================================================================================================
	@SuppressWarnings("unchecked")
	public List<FriendshipBean> getFriendsByMethod(String query) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<FriendshipBean> lpb;
		try {
			lpb = (List<FriendshipBean>) session.createQuery(query).list();
			
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}
}
