package com.senla.training.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.senla.training.interfaces.IAbstractDAO;
import com.senla.training.model.Model;

public abstract class AbstractDAO<T extends Model> implements IAbstractDAO<T> {

	private final Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Session session, String sortBy) {
		return session.createCriteria(this.entityClass).addOrder(Order.asc(sortBy)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findEntityById(Session session, Integer id) {
		return (T) session.get(this.entityClass, id);
	}

	@Override
	public void update(Session session, T entity) {
		session.update(entity);
	}

	@Override
	public void add(Session session, T entity) {
		session.save(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void delete(Session session, Integer id) {
		T entity = (T) session.get(this.entityClass, id);
		session.delete(entity);
	}
}