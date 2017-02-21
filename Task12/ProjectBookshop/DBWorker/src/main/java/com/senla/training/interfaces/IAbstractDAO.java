package com.senla.training.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.senla.training.model.Model;

public interface IAbstractDAO<T extends Model> {

	List<T> findAll(Session session, String sortBy);

	T findEntityById(Session session, Integer id);

	void update(Session session, T entity);

	void add(Session session, T entity);

	void delete(Session session, Integer id);

}