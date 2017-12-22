package com.ccc.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.dao.interfacedao.IPictureDAO;

@Transactional
public class PictureDAO implements IPictureDAO {

	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public PictureBean insert(PictureBean pic) {
		Session session = sessionFactory.getCurrentSession();
		
		try{						
			session.save(pic);			
			return pic;
		} catch(Exception e) {
			e.printStackTrace();
//			System.out.println("--------------");
			return null;
		}
	}

	@Override
	public PictureBean update(PictureBean pic) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.saveOrUpdate(pic);
			return pic;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PictureBean delete(PictureBean pic) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.delete(pic);
			return pic;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PictureBean getByPrimaryKey(int pic_id) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			PictureBean pic = (PictureBean) session.get(PictureBean.class, pic_id);
			return pic;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PictureBean> getAll() {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from PictureBean");
			List<PictureBean> pics = query.list();
			return pics;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PictureBean> getPicturesByM_id(String m_id) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from PictureBean where m_id=:m_id");
			query.setString("m_id", m_id);
			List<PictureBean> pics = query.list();
			return pics;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PictureBean> getPicturesByC_id(int c_id) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from PictureBean where c_id=:c_id");
			query.setInteger("c_id", c_id);
			List<PictureBean> pics = query.list();
			return pics;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PictureBean> getPicturesByMethod(String query) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query q = session.createQuery(query);
			List<PictureBean> pics = q.list();
			return pics;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
