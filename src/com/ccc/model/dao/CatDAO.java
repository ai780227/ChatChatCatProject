package com.ccc.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.ICatDAO;

@Transactional
public class CatDAO implements ICatDAO {
	
	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public CatBean insert(CatBean cat) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.save(cat);
			return cat;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CatBean update(CatBean cat) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(cat);
			return cat;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CatBean delete(CatBean cat) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.delete(cat);
			return cat;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CatBean getByPrimaryKey(Integer c_id) {
		Session session = sessionFactory.getCurrentSession();
		try{
			CatBean cat = (CatBean) session.get(CatBean.class, c_id);
			return cat;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CatBean> getAll() {
		Session session = sessionFactory.getCurrentSession();
		try{
			Query query = session.createQuery("from CatBean");
			List<CatBean> cats = query.list();
			return cats;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CatBean> getCatsByM_id(String m_id) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from CatBean where m_id=:m_id");
			query.setString("m_id", m_id);
			List<CatBean> cats = query.list();
			return cats;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CatBean> getCatsByMethod(String query) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query q = session.createQuery(query);
			List<CatBean> cats = q.list();
			return cats;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
