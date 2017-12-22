package com.ccc.model.dao;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.*;

public class DefaultFactory {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	private static SessionFactory configureSessionFactory()
			throws HibernateException {
		if (sessionFactory != null)
			return sessionFactory;
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return configureSessionFactory();
	}

	public static void shutdown() {
		sessionFactory.close();
	}
}
