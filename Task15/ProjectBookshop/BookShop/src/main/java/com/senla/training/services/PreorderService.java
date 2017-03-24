package com.senla.training.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.dao.BookDAO;
import com.senla.training.dao.PreorderDAO;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookDAO;
import com.senla.training.interfaces.IPreorderDAO;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.model.Preorder;
import com.senla.training.tools.CSVUtility;
import com.senla.training.tools.ConverterCSV;

public class PreorderService implements IPreorderService {
	private IPreorderDAO preorderDao;
	private IBookDAO bookDao;
	private final Logger log = Logger.getLogger(OrderService.class);
	private static final String RESOURCES_PREORDERS_CSV = "src/main/resources/Preorders.csv";
	private static final String ID = "id";

	public PreorderService(PreorderDAO preorderDao, BookDAO bookDao) {
		this.preorderDao = preorderDao;
		this.bookDao = bookDao;
	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public boolean writePreordersToCsv() {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Preorder> allPreorders = preorderDao.findAll(session, ID);
			List<String> strPreorders = new ArrayList<String>();
			for (Preorder preorder : allPreorders) {
				strPreorders.add(ConverterCSV.preorderToString(preorder));
			}
			CSVUtility.writeToCsv(strPreorders, RESOURCES_PREORDERS_CSV);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean readPreordersFromCsv() {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<String> strPreorders = CSVUtility.readFromCsv(RESOURCES_PREORDERS_CSV);
			for (String str : strPreorders) {
				Preorder preorder = (Preorder) ConverterCSV.stringToPreorder(bookDao, session, str);
				preorderDao.add(session, preorder);
			}
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
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
	public synchronized List<Preorder> showAllPreorders(String sortBy) {
		Transaction trans = null;
		List<Preorder> result = new ArrayList<>();
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			result = preorderDao.findAll(session, sortBy);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return result;
	}
}