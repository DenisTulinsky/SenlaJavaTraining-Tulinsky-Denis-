package com.senla.training.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.senla.training.model.Book;

public interface IBookDAO extends IAbstractDAO<Book>  {

	public List<Book> getUnwanted(Session session, String sortBy);
	public List<Book> findAll(Session session, String sortBy);
}