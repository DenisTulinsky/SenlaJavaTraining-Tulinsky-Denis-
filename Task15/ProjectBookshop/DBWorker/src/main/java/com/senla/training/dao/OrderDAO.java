package com.senla.training.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.senla.training.enums.Status;
import com.senla.training.interfaces.IOrderDAO;
import com.senla.training.model.Order;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {
	private static final String PRICE = "price";
	private static final String STATUS = "status";
	private static final String EXECUTION_DATE = "executionDate";

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getExecOrders(Session session, Date start, Date end, String sortBy) {
		Criteria crit = getCritExecOrders(start, end).getExecutableCriteria(session);
		return crit.addOrder(org.hibernate.criterion.Order.asc(sortBy)).list();
	}

	@Override
	public Long getExecOrdersCount(Session session, Date start, Date end) {
		Criteria crit = getCritExecOrders(start, end).getExecutableCriteria(session);
		crit.setProjection(Projections.rowCount());
		return (Long) crit.uniqueResult();
	}

	@Override
	public Long getExecOrdersMoney(Session session,  Date start, Date end) {
		Criteria crit = getCritExecOrders(start, end).getExecutableCriteria(session);
		crit.setProjection(Projections.sum(PRICE));
		return (Long) crit.uniqueResult();
	}

	@Override
	public DetachedCriteria getCritExecOrders(Date start, Date end) {
		DetachedCriteria detCrit = DetachedCriteria.forClass(Order.class);
		detCrit.add(Restrictions.eq(STATUS, Status.EXECUTED))
				.add(Restrictions.between(EXECUTION_DATE, start, end)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return detCrit;
	}
}
