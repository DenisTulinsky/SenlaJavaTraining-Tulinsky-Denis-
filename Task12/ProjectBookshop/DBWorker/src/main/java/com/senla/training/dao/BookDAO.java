package com.senla.training.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.senla.training.interfaces.IBookDAO;
import com.senla.training.model.Book;
import com.senla.training.properties.PropertyFactory;

public class BookDAO extends AbstractDAO<Book> implements IBookDAO {
	private static final String ORDER_BY = "orderBy";
	private static final String MONTHS = "months";
	private static final String MONTHS_UNWANTED = "monthsUnwanted";

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> getUnwanted(Session session, String sortBy) {
		Query query = session.createQuery(
				"FROM Book where (TIMESTAMPDIFF(MONTH, arrivalDate, curdate()) >=:months ) order by :orderBy");
		query.setParameter(MONTHS, Integer.valueOf(PropertyFactory.getProps().getValue(MONTHS_UNWANTED)));
		query.setParameter(ORDER_BY, sortBy);
		return query.list();
	}
}
