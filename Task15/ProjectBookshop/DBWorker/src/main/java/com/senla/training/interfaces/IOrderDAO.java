package com.senla.training.interfaces;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.senla.training.model.Order;

public interface IOrderDAO extends IAbstractDAO<Order> {
	
	public List<Order> getExecOrders(Session session, Date start, Date end, String sortBy);
	public Long getExecOrdersCount(Session session, Date start, Date end);
	public Long getExecOrdersMoney(Session session, Date start, Date end);
	public DetachedCriteria getCritExecOrders(Date start, Date end);
}