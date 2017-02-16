package com.senla.training.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.dao.PreorderDAO;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.model.Preorder;

public class PreorderService implements IPreorderService {
	private IConverterReadableString converterToString;
	private PreorderDAO preorderDao;
	private final Logger log = Logger.getLogger(OrderService.class);

	public PreorderService(IConverterReadableString converterToString, PreorderDAO preorderDao) {
		this.preorderDao = preorderDao;
		this.converterToString = converterToString;
	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public synchronized boolean addPreorder(Preorder preorder) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			preorderDao.add(session, preorder);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public synchronized boolean updateStatus(Integer bookId) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			preorderDao.updatePreordersStatus(session, bookId);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public synchronized List<String> showAllPreorders(String sortBy) {
		Transaction trans = null;
		List<String> result = new ArrayList<>();
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Preorder> preorders = preorderDao.findAll(session, sortBy);
			trans.commit();
			for (Preorder p : preorders) {
				result.add(converterToString.convert(p));
			}
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return result;
	}
}