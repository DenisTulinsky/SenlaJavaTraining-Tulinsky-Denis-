package com.senla.training.dao;

import java.util.Calendar;
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
	public List<Order> getExecOrders(Session session, Calendar cal1, Calendar cal2, String sortBy) {
		Criteria crit = getCritExecOrders(cal1, cal2).getExecutableCriteria(session);
		return crit.addOrder(org.hibernate.criterion.Order.asc(sortBy)).list();
	}

	@Override
	public Long getExecOrdersCount(Session session, Calendar cal1, Calendar cal2) {
		Criteria crit = getCritExecOrders(cal1, cal2).getExecutableCriteria(session);
		crit.setProjection(Projections.rowCount());
		return (Long) crit.uniqueResult();
	}

	@Override
	public Long getExecOrdersMoney(Session session, Calendar cal1, Calendar cal2) {
		Criteria crit = getCritExecOrders(cal1, cal2).getExecutableCriteria(session);
		crit.setProjection(Projections.sum(PRICE));
		return (Long) crit.uniqueResult();
	}

	@Override
	public DetachedCriteria getCritExecOrders(Calendar cal1, Calendar cal2) {
		DetachedCriteria detCrit = DetachedCriteria.forClass(Order.class);
		detCrit.add(Restrictions.eq(STATUS, Status.EXECUTED))
				.add(Restrictions.between(EXECUTION_DATE, cal1.getTime(), cal2.getTime()));
		return detCrit;
	}
}
