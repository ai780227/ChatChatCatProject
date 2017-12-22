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

import com.ccc.model.bean.FriendshipNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.IFriendshipNoticeDAO;
/*
 * 加友通知  DAO
 * table：加友通知 FriendshipNotice
 */
@Transactional
public class FriendshipNoticeDAO implements IFriendshipNoticeDAO {
	Transaction tx = null;
	private int yn = 0;
	
	//Spring sessionFactory
			private SessionFactory sessionFactory = null;
			public void setSessionFactory(SessionFactory sessionFactory) {
				this.sessionFactory = sessionFactory;
			}

//===========================================insert=============================================================================================================
	public FriendshipNoticeBean insert(FriendshipNoticeBean fsNB) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(fsNB);
			return fsNB;
		} catch (Exception ex) {
			return null;
		}
		
	}
	
//==========================================update==============================================================================================================	
	public FriendshipNoticeBean update(FriendshipNoticeBean fsNB) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE FriendshipNoticeBean SET  fri_read=:fri_read , fri_type=:fri_type , m_name_from=:m_name_from WHERE m_id_to=:m_id_to and m_id_from=:m_id_from";
		Query query = session.createQuery(hql);
		query.setString("m_id_to", fsNB.getMember_to().getM_id());
		query.setString("m_id_from", fsNB.getMember_from().getM_id());
		query.setString("m_name_from", fsNB.getM_name_from());
		query.setInteger("fri_read", fsNB.getFri_read());
		query.setInteger("fri_type", fsNB.getFri_type());
		try {
			yn = query.executeUpdate();
			if (yn == 0) {
				return null;
			} else {
				return fsNB;
			}
		} catch (Exception ex) {
			return null;
		}
		
	}
	
//==========================================delete==============================================================================================================
	public FriendshipNoticeBean delete(FriendshipNoticeBean fsNB) {
		Session session = sessionFactory.getCurrentSession();
		int yn = 0;
		String hql = "DELETE FriendshipNoticeBean WHERE m_id_to=:m_id_to and m_id_from=:m_id_from";
		Query query = session.createQuery(hql);
		query.setString("m_id_to", fsNB.getMember_to().getM_id());
		query.setString("m_id_from", fsNB.getMember_from().getM_id());
		try {
			yn=query.executeUpdate();
			if (yn == 0) {
				return null;
			} else {
				return fsNB;
			}
		} catch (Exception ex) {
			return null;
		}
		
	}
	
	
//=======================================getByPrimaryKey=================================================================================================================
	@Override
	public FriendshipNoticeBean getByPrimaryKey(int fri_notid) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(FriendshipNoticeBean.class);
			criteria.add(Restrictions.eq("fri_notid", fri_notid));
			List act = criteria.list();
			if (act.size()!=0) {
				Iterator<FriendshipNoticeBean> list = act.iterator();
				while (list.hasNext()) {
					FriendshipNoticeBean users = (FriendshipNoticeBean) list.next();
					return users;
					}
			}else{
				return null;	
			}	
		} catch (Exception ex) {
			return null;
		}
		return null;
		
	}
	
//=======================================getAll===================================================================================================================	
	@Override
	public List<FriendshipNoticeBean> getAll() {
		List<FriendshipNoticeBean> listAll;
		Iterator listA = null;
		Session session = sessionFactory.getCurrentSession();
		try {
		String sql = "from FriendshipNoticeBean FriendshipNotice";
		Query query = session.createQuery(sql);
		listAll = (List<FriendshipNoticeBean>) query.list();
			if(listAll.size()!=0){
				return listAll;
			}else{
				return null;
			}
		}catch (Exception ex) {
			return null;
		}
		
	}		
		
	
	//=======================================getFriendshipNoticeByUser===================================================================================================================	
//		@Override
		public List<FriendshipNoticeBean> getFriendshipNoticeByUser(MemberBean user) {
			List<FriendshipNoticeBean> fsNoticeList;
			Session session = sessionFactory.getCurrentSession();
			
			try {
				Query query = session.createQuery("from FriendshipNoticeBean where m_id_to=:m_id_to");
				query.setString("m_id_to", user.getM_id());
				fsNoticeList = query.list();
				return fsNoticeList;
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
}		
	

