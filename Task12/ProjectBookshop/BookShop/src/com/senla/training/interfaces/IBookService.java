package com.senla.training.interfaces;

import java.util.List;

import com.senla.training.model.Book;

public interface IBookService {

	public boolean addBook(Book book);

	public boolean removeBook(Integer id);

	public List<String> showAllBooks(String sortBy);

	public List<String> viewBookDescription(Integer id);

	public List<String> showUnwantedBooks(String sortBy);

}