package com.senla.training.interfaces;

import java.util.List;

import com.senla.training.model.Book;

public interface IBookService {

	public boolean addBook(Book book);

	public boolean removeBook(String title);

	public List<String> showAllBooks(String sortBy);

	public List<String> viewBookDescription(String title);

	public List<String> showUnwantedBooks(String sortBy);

}