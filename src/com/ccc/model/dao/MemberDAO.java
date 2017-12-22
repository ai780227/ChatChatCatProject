package com.ccc.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.IMemberDAO;

@Transactional
public class MemberDAO implements IMemberDAO {
	
	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MemberBean insert(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.save(mem);		
			return mem;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MemberBean update(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.saveOrUpdate(mem);	
			return mem;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MemberBean delete(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.delete(mem);
			return mem;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MemberBean getByPrimaryKey(String m_id) {
		Session session = sessionFactory.getCurrentSession();
				
		try{
			MemberBean mem = (MemberBean) session.get(MemberBean.class, m_id);
			return mem;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<MemberBean> getAll() {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from MemberBean");
			List<MemberBean> members = query.list();
			return members;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<MemberBean> getMembersByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from MemberBean where m_name like :m_name");
			query.setString("m_name", "%" + name + "%");
			List<MemberBean> members = query.list();
			return members;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<MemberBean> getMembersByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from MemberBean where m_email like :email");
			query.setString("email", "%" + email + "%");
			List<MemberBean> members = query.list();
			return members;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<MemberBean> getMembersByMethod(String query) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query q = session.createQuery(query);
			List<MemberBean> members = q.list();
			return members;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
