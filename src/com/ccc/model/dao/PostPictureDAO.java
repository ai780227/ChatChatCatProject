package com.ccc.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.dao.interfacedao.IPostPictureDAO;

@Transactional
public class PostPictureDAO implements IPostPictureDAO {
	
	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public PostPictureBean insert(PostPictureBean postpic) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.save(postpic);
			return postpic;
		} catch(Exception e) {
			return null;
		}
	}	

	@Override
	public PostPictureBean delete(PostPictureBean postpic) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			session.delete(postpic);
			return postpic;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public PostPictureBean getByPrimaryKey(int post_id, int pic_id) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session
					.createQuery("from PostPictureBean where post_id=:post_id and pic_id=:pic_id");
			query.setInteger("post_id", post_id);
			query.setInteger("pic_id", pic_id);
			PostPictureBean postpic = (PostPictureBean) query.list().get(0);
			return postpic;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<PostPictureBean> getAll() {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session.createQuery("from PostPictureBean");
			List<PostPictureBean> postpics = query.list();
			return postpics;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<PostPictureBean> getByPicId(int pic_id) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session
					.createQuery("from PostPictureBean where pic_id=:pic_id");
			query.setInteger("pic_id", pic_id);
			List<PostPictureBean> postpics = query.list();
			return postpics;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<PostPictureBean> getByPostId(int post_id) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query query = session
					.createQuery("from PostPictureBean where post_id=:post_id");
			query.setInteger("post_id", post_id);
			List<PostPictureBean> postpics = query.list();
			return postpics;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<PostPictureBean> getPicturesByMethod(String query) {
		Session session = sessionFactory.getCurrentSession();
		
		try{
			Query q = session.createQuery(query);
			List<PostPictureBean> postpics = q.list();
			return postpics;
		} catch(Exception e) {
			return null;
		}
	}

}
