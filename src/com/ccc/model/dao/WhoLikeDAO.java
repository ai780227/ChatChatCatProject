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
import com.ccc.model.bean.MessageBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.dao.interfacedao.IWhoLikeDAO;

@Transactional
public class WhoLikeDAO implements IWhoLikeDAO {

	// Spring sessionFactory
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public WhoLikeBean insert(WhoLikeBean WlB) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(WlB);
			return WlB;
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public WhoLikeBean update(WhoLikeBean WlB) {
		return null;
	}

	@Override
	public WhoLikeBean delete(WhoLikeBean WlB) {
		Session session = sessionFactory.getCurrentSession();
		int yn = 0;
		String hql = "DELETE WhoLikeBean WHERE post_id=:post_id and m_id=:m_id ";
		Query query = session.createQuery(hql);
		query.setInteger("post_id", WlB.getPost().getPost_id());
		query.setString("m_id", WlB.getMember().getM_id());
		try {
			yn = query.executeUpdate();
			if (yn != 1) {
				return null;
			} else {
				return WlB;
			}
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public WhoLikeBean getByPrimaryKey(int post_id, String m_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from WhoLikeBean where post_id=:post_id and m_id=:m_id");
		query.setInteger("post_id", post_id);
		query.setString("m_id", m_id);
		try {
			List<WhoLikeBean> act = query.list();
			if (act.size() != 0) {
				WhoLikeBean wb = act.get(0);
				return wb;
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public List<WhoLikeBean> getAll() {
		List<WhoLikeBean> listAll;
		Iterator listA = null;
		Session session = sessionFactory.getCurrentSession();
		String sql = "from WhoLikeBean WhoLike";
		try {
			Query query = session.createQuery(sql);
			listAll = (List<WhoLikeBean>) query.list();
			if (listAll.size() != 0) {
				for (int i = 0; i < listAll.size(); i++) {
					WhoLikeBean WB = listAll.get(i);
				}
				return listAll;
			} else {
				return null;
			}

		} catch (Exception ex) {
			return null;
		}
	}
		
		@Override
		public List<WhoLikeBean> getByPostId(int post_id){
			Session session = sessionFactory.getCurrentSession();
			String HQL="from WhoLikeBean where post_id=:post_id";
			try{
			Query query = session.createQuery(HQL);
			query.setInteger("post_id", post_id);
			List<WhoLikeBean> wholikelist=query.list();
			return wholikelist;
			}catch(Exception ex){
				return null;
			}
		}

	
}