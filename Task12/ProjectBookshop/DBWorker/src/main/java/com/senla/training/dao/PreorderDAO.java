package com.senla.training.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import com.senla.training.enums.Status;
import com.senla.training.interfaces.IPreorderDAO;
import com.senla.training.model.Preorder;

public class PreorderDAO extends AbstractDAO<Preorder> implements IPreorderDAO {

	private static final String BOOK_ID = "bookId";
	private static final String STATUS = "status";
	private static final String ID = "id";
	private static final String BOOK = "book";
	private static final String COUNT = "count";

	@Override
	@SuppressWarnings("unchecked")
	public List<Preorder> findAll(Session session, String sortBy) {
		Criteria criteria = session.createCriteria(Preorder.class)
				.setProjection(Projections.projectionList().add(Projections.groupProperty(BOOK).as(BOOK))
						.add(Projections.rowCount(), COUNT).add(Projections.property(STATUS).as(STATUS))
						.add(Projections.property(ID).as(ID)));
		criteria.createAlias(BOOK, "b").addOrder(Order.asc(sortBy));
		criteria.setResultTransformer(Transformers.aliasToBean(Preorder.class));
		List<Preorder> list = criteria.list();
		System.out.println(list);
		return list;
	}

	@Override
	public void updatePreordersStatus(Session session, Integer bookId) {
		Query query = session.createQuery("update Preorder as pr set pr.status = :status where book.id = :bookId");
		query.setParameter(BOOK_ID, bookId);
		query.setParameter(STATUS, Status.EXECUTED);
		query.executeUpdate();
	}
}
