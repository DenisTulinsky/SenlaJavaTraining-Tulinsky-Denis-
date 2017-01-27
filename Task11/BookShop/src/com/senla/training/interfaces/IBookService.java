package com.senla.training.interfaces;

import java.util.Comparator;
import java.util.List;

import com.senla.training.model.Book;

public interface IBookService {

	public void addBook(Book book);

	public void removeBook(String title);

	public List<String> showAllBooks(String sortBy);

	public List<String> viewBookDescription(String title);

	public List<String> showUnwantedBooks(Comparator<IBook> com);

}