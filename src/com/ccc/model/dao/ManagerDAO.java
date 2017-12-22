package com.ccc.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.ManagerBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.IManagerDAO;

@Transactional
public class ManagerDAO implements IManagerDAO {
	
	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ManagerBean insert(ManagerBean mgr) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.save(mgr);
			return mgr;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public ManagerBean update(ManagerBean mgr) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(mgr);
			return mgr;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public ManagerBean delete(ManagerBean mgr) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.delete(mgr);
			return mgr;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public ManagerBean getByPrimaryKey(String mgr_id) {
		Session session = sessionFactory.getCurrentSession();

		try{
			ManagerBean mgr = (ManagerBean) session.get(ManagerBean.class, mgr_id);
			return mgr;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<ManagerBean> getAll() {
		Session session = sessionFactory.getCurrentSession();

		try{
			Query query = session.createQuery("from ManagerBean");
			List<ManagerBean> mgrs = query.list();
			return mgrs;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<ManagerBean> getManagersByName(String name) {
		Session session = sessionFactory.getCurrentSession();

		try{
			Query query = session
					.createQuery("from ManagerBean where mgr_name=:mgr_name");
			query.setString("mgr_name", name);
			List<ManagerBean> mgrs = query.list();
			return mgrs;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<ManagerBean> getManagersByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();

		try{
			Query query = session
					.createQuery("from ManagerBean where mgr_email=:mgr_email");
			query.setString("mgr_email", email);
			List<ManagerBean> mgrs = query.list();
			return mgrs;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<ManagerBean> getManagersByMethod(String query) {
		Session session = sessionFactory.getCurrentSession();

		try{
			Query q = session.createQuery(query);
			List<ManagerBean> mgrs = q.list();
			return mgrs;
		} catch(Exception e) {
			return null;
		}
	}

}
