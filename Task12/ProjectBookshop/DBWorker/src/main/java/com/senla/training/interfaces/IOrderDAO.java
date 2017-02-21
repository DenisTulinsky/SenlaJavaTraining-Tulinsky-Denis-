package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.senla.training.model.Order;

public interface IOrderDAO extends IAbstractDAO<Order> {
	
	public List<Order> getExecOrders(Session session, Calendar cal1, Calendar cal2, String sortBy);
	public Long getExecOrdersCount(Session session, Calendar cal1, Calendar cal2);
	public Long getExecOrdersMoney(Session session, Calendar cal1, Calendar cal2);
	public DetachedCriteria getCritExecOrders(Calendar cal1, Calendar cal2);
}