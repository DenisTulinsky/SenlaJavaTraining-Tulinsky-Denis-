package com.senla.training.hibernateUtils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {

			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable e) {

			System.err.println("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		}
		return session;
	}
}