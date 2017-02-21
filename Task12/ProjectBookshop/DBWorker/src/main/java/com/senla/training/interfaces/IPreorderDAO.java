package com.senla.training.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.senla.training.model.Preorder;

public interface IPreorderDAO extends IAbstractDAO<Preorder> {

	List<Preorder> findAll(Session session, String sortBy);

	public void updatePreordersStatus(Session session, Integer bookId);

}